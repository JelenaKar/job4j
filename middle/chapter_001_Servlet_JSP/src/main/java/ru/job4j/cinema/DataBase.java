package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Объект хранилища в базе данных.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class DataBase implements Storage {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final Storage INSTANCE = new DataBase();
    private static final Logger LOG = LogManager.getLogger(DataBase.class);

    private DataBase() {
        Properties config = new Properties();
        try (InputStream in = ru.job4j.crud.DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static Storage getInstance() {
        return INSTANCE;
    }

    /**
     * Возвращает список всех посадочных мест для заданного зала.
     * @param idhall - идентификационный номер зала.
     * @return список всех мест.
     */
    public List<Place> getAllPlaces(int idhall) {
        List<Place> res = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM halls WHERE hallnum = ? ORDER BY rownum, colnum")) {
            st.setLong(1, idhall);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                res.add(new Place(rs.getInt("rownum"), rs.getInt("colnum"), rs.getBoolean("isfree"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return res;
    }

    /**
     * Возвращает информацию для заданного места.
     * @param idhall - номер зала.
     * @param row - номер ряда.
     * @param col - номер места.
     * @return место в виде объекта Place.
     */
    public Place getPlace(int idhall, int row, int col) {
        Place res = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM halls WHERE hallnum = ? "
                     + "AND rownum = ? AND colnum = ?")) {
            st.setLong(1, idhall);
            st.setInt(2, row);
            st.setInt(3, col);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                res = new Place(rs.getInt("rownum"), rs.getInt("colnum"), rs.getBoolean("isfree"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return res;
    }

    /**
     * Совершает бронирование и оплату места.
     * @param idhall - номер зала.
     * @param row - номер ряда.
     * @param col - номер места.
     * @param fullname - ФИО пользователя.
     * @param phone - номер телефона пользователя в международном формате.
     * @return true в случае успешного завершения транзакции, false - в противном случае.
     */
    public boolean doPayment(int idhall, int row, int col, String fullname, String phone) {
        boolean res = false;
        Semaphore semaphore = new Semaphore(1);
        try (Connection connection = SOURCE.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Place place = INSTANCE.getPlace(idhall, row, col);
                semaphore.tryAcquire(5, TimeUnit.SECONDS);
                if (!place.isFree()) {
                    semaphore.release();
                    throw new UnsupportedOperationException("Place is already occupied!");
                }
                try (PreparedStatement bookStatement = connection.prepareStatement(
                        "UPDATE halls SET isfree = false WHERE hallnum = ? AND rownum = ? AND colnum = ?"
                )) {
                    bookStatement.setLong(1, idhall);
                    bookStatement.setInt(2, row);
                    bookStatement.setInt(3, col);
                    bookStatement.executeUpdate();
                }
                semaphore.release();
                try (PreparedStatement debitStatement = connection.prepareStatement(
                        "UPDATE accounts SET amount = (amount-?) WHERE fullname = ? AND phone = ?;"
                )) {
                    debitStatement.setDouble(1, place.getPrice());
                    debitStatement.setString(2, fullname);
                    debitStatement.setString(3, phone);
                    int affectedRows = debitStatement.executeUpdate();
                    if (affectedRows == 0) {
                        throw new UnsupportedOperationException("No such account!");
                    }
                }
                connection.commit();
               res = true;
            } catch (Exception e) {
                connection.rollback();
                LOG.warn(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return res;
    }
}
