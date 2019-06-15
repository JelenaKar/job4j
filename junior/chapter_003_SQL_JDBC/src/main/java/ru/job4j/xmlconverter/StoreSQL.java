package ru.job4j.xmlconverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, инкапсулирующий работу с БД.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;

    private Connection connect;

    public StoreSQL(Config config) throws SQLException {
        this.config = config;
        connect = DriverManager.getConnection(this.config.get("url"));
    }
    /**
     * Метод, генерирующий БД с целочисленной последовательностью в качестве значения столбца.
     * @param size - размерность последовательности: 1 ...size.
     */
    public void generate(int size) throws SQLException {
        try (Statement simple = connect.createStatement()) {
            connect.setAutoCommit(false);
            try {
                simple.executeUpdate("CREATE TABLE IF NOT EXISTS entry (field integer)");
                simple.executeUpdate("DELETE FROM entry");
                try (PreparedStatement prepared = connect.prepareStatement("INSERT INTO entry (field) VALUES (?)")) {
                    int count = 0;
                    for (int i = 1; i <= size; i++) {
                        prepared.setInt(1, i);
                        prepared.addBatch();
                        count++;
                        if (count % 1000 == 0 || count == size) {
                            prepared.executeBatch();
                            connect.commit();
                        }
                    }
                }
            } catch (SQLException e) {
                connect.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Метод, выгружающий данные из БД.
     * @return последовательность в виде списка объектов Entry.
     */
    public List<XmlUsage.Entry> load() throws SQLException {
        List<XmlUsage.Entry> result = new ArrayList<>();
        try (Statement st = this.connect.createStatement();
             ResultSet rs = st.executeQuery("SELECT field FROM entry")) {
            while (rs.next()) {
                result.add(new XmlUsage.Entry(rs.getInt(1)));
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
