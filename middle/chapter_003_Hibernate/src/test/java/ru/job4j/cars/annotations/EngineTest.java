package ru.job4j.cars.annotations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import ru.job4j.cars.Actions;
import ru.job4j.utils.FileSqlParser;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;

public class EngineTest {

    private final SessionFactory sf = new Configuration().configure("hsqldb.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final static Connection CONNECTION = init();

    public static Connection init() {
        try (InputStream in = EngineTest.class.getClassLoader().getResourceAsStream("app.properties")) {
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
                    new File(EngineTest.class.getClassLoader().getResource("prepareDB.sql").getFile()),
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
                    new File(EngineTest.class.getClassLoader().getResource("dropDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddEngineThenInsertInDBAndWhenRemoveThenDeleteItFromDb() {
        int amount = dao.findAll(Engine.class, sf).size();
        Engine added = dao.add(new Engine(), sf);
        assertEquals(dao.findAll(Engine.class, sf).size(), amount + 1);
        assertEquals(dao.findById(added.getId(), Engine.class, sf), added);
        dao.delete(added, sf);
        assertEquals(dao.findAll(Engine.class, sf).size(), amount);
        assertNull(dao.findById(added.getId(), Engine.class, sf));
    }

    @Test
    public void whenUpdateCarListForEngineThenAddOrRemoveCascadeItInTheCarTable() {
        Engine engine2 = dao.findById(2, Engine.class, sf);
        List<Car> cars = engine2.getCars();
        System.out.println(dao.findById(3, Engine.class, sf).getCars());
        assertEquals(1, cars.size());
        cars.add(Car.of(engine2));
        dao.update(engine2, sf);
        assertEquals(2, dao.findById(2, Engine.class, sf).getCars().size());
        assertEquals(2, dao.findByField(Car.class, sf, "engine_id", 2).size());
        cars.remove(cars.size() - 1);
        dao.update(engine2, sf);
        assertEquals(1, dao.findById(2, Engine.class, sf).getCars().size());
        assertEquals(1, dao.findByField(Car.class, sf, "engine_id", 2).size());
    }

    @Test
    public void whenRemoveEngineThenRemoveCascadeCarsWithThisEngine() {
        Engine engine1 = dao.findById(1, Engine.class, sf);
        assertEquals(2, dao.findByField(Car.class, sf, "engine_id", 1).size());
        dao.delete(engine1, sf);
        assertEquals(0, dao.findByField(Car.class, sf, "engine_id", 1).size());
    }
}