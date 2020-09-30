package ru.job4j.cars.xml;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.cars.Actions;
import ru.job4j.utils.FileSqlParser;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class EngineXMLTest {
    private final SessionFactory sf = new Configuration().configure("hsqldb.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final static Connection CONNECTION = init();

    public static Connection init() {
        try (InputStream in = EngineXMLTest.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @BeforeClass
    public static void createDB() {
        try {
            FileSqlParser.readSqlFile(
                    new File(EngineXMLTest.class.getClassLoader().getResource("prepareDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void dropDB() {
        try {
            FileSqlParser.readSqlFile(
                    new File(EngineXMLTest.class.getClassLoader().getResource("dropDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddEngineThenInsertInDBAndWhenRemoveThenDeleteItFromDb() {
        int amount = dao.findAll(EngineXML.class, sf).size();
        EngineXML added = dao.add(new EngineXML(), sf);
        assertEquals(dao.findAll(EngineXML.class, sf).size(), amount + 1);
        assertEquals(dao.findById(added.getId(), EngineXML.class, sf), added);
        dao.delete(added, sf);
        assertEquals(dao.findAll(EngineXML.class, sf).size(), amount);
        assertNull(dao.findById(added.getId(), EngineXML.class, sf));
    }

    @Test
    public void whenUpdateCarListForEngineThenAddOrRemoveCascadeItInTheCarTable() {
        EngineXML engine2 = dao.findById(2, EngineXML.class, sf);
        List<CarXML> cars = engine2.getCars();
        assertEquals(1, cars.size());
        cars.add(CarXML.of(engine2));
        dao.update(engine2, sf);
        assertEquals(2, dao.findById(2, EngineXML.class, sf).getCars().size());
        assertEquals(2, dao.findByField(CarXML.class, sf, "engine_id", 2).size());
        cars.remove(cars.size() - 1);
        dao.update(engine2, sf);
        assertEquals(1, dao.findById(2, EngineXML.class, sf).getCars().size());
        assertEquals(1, dao.findByField(CarXML.class, sf, "engine_id", 2).size());
    }

    @Test
    public void whenRemoveEngineThenRemoveCascadeCarsWithThisEngine() {
        EngineXML engine1 = dao.findById(1, EngineXML.class, sf);
        assertEquals(2, dao.findByField(CarXML.class, sf, "engine_id", 1).size());
        dao.delete(engine1, sf);
        assertEquals(0, dao.findByField(CarXML.class, sf, "engine_id", 1).size());
    }
}