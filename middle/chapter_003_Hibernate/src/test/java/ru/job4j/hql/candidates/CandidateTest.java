package ru.job4j.hql.candidates;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.utils.FileSqlParser;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

public class CandidateTest {

    private final SessionFactory sf = new Configuration().configure("hsqldb.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    private final static Connection CONNECTION = init();

    public static Connection init() {
        try (InputStream in = CandidateTest.class.getClassLoader().getResourceAsStream("app.properties")) {
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
                    new File(CandidateTest.class.getClassLoader().getResource("addTestData.sql").getFile()),
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
                    new File(CandidateTest.class.getClassLoader().getResource("dropCandidateDB.sql").getFile()),
                    CONNECTION
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSelectAllThenReceiveListOfAllCandidates() {
        System.out.println("List of candidates:");
        List<Candidate> candidates = dao.findAll(Candidate.class, sf);
        assertEquals(candidates.size(), 3);
        candidates.forEach(System.out::println);
    }

    @Test
    public void whenSelectByIdEqualsTwoThenReceivePetrovPetr() {
        System.out.println("Select candidate with id = 2");
        List<Candidate> petr = dao.findByField(Candidate.class, sf, "id", 2);
        assertEquals(petr.size(), 1);
        assertEquals(petr.get(0).getName(), "Петров Петр");
        System.out.println(petr.get(0));
    }

    @Test
    public void whenSelectByNameEqualsIvanovThenReceiveCandidate() {
        System.out.println("Select candidate with name = 'Иванов Иван'");
        List<Candidate> ivan = dao.findByField(Candidate.class, sf, "name", "Иванов Иван");
        assertEquals(ivan.size(), 1);
        assertEquals(ivan.get(0).getName(), "Иванов Иван");
        assertEquals(ivan.get(0).getExperience(), 5);
        assertEquals(ivan.get(0).getSalary(), 200000);
        System.out.println(ivan.get(0));
    }

    @Test
    public void whenEditCandidateDataThenReceiveUpdatedCandidate() {
        System.out.println("Update experience and salary of the candidate");
        dao.update(Candidate.class, sf, Map.of("experience", 1, "salary", 50000), Map.of("id", 3));
        Candidate marfa = dao.findByField(Candidate.class, sf, "id", 3).get(0);
        assertEquals(marfa.getExperience(), 1);
        assertEquals(marfa.getSalary(), 50000);
        System.out.println(marfa);
    }

    @Test
    public void whenDeleteCandidateThenCouldNotToFindHim() {
        System.out.println("Delete test candidate with id = 4");
        dao.add(Candidate.of("Deleting Candidate", 10, 100000), sf);
        assertEquals(dao.findByField(Candidate.class, sf, "id", 4).size(), 1);
        dao.delete(Candidate.class, sf, Map.of("id", 4));
        assertEquals(dao.findByField(Candidate.class, sf, "id", 4).size(), 0);
    }

}