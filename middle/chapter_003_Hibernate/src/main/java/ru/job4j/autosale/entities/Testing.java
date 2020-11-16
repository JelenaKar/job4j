package ru.job4j.autosale.entities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.cars.Actions;

public class Testing {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();

    public static void main(String[] args) {
        Testing testing = new Testing();
        //testing.dao.findAll(Auto.class, testing.sf).forEach(System.out::println);
        /*Auto updated = testing.dao.findByField(Auto.class, testing.sf, "model_id", 78).get(0);
        updated.setBroken(true);
        testing.dao.update(updated, testing.sf);
        Auto inserted = Auto.of(Make.of(13), Model.of(78), 1987,
                                BodyStyle.of(2), 5, EngineType.of(1),
                                DriveUnit.of(3), Transmission.of(1), "2.2 quattro MT (165 л.с.)",
                                true, 200000, false, "Серебряный", 2
                );
        testing.dao.add(inserted, testing.sf);*/
        /*Photo photo = new Photo();
        photo.setName("testing.jpg");
        photo.setFolder("template");*/
        System.out.println(testing.dao.findById(5, Photo.class, testing.sf));
        //testing.dao.findAll(Ad.class, testing.sf).forEach(System.out::println);
    }
}