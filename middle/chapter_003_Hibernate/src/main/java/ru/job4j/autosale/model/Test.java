package ru.job4j.autosale.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    public static void main(String[] args) {
        Test test = new Test();
        Map<String, Integer> peoples = new HashMap<>(Map.of("Lenka", 33, "Irka", 32));
        System.out.println(peoples);
        Integer age = peoples.remove("Lenka");
        System.out.println(age);
        System.out.println(peoples);
        //System.out.println(test.dao.getOneBrandOnly(test.sf));
    }
}
