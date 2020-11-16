package ru.job4j.cars.xml;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.cars.Actions;

public class TestClass {
    private final SessionFactory sf = new Configuration().configure("postgresql.cfg.xml").buildSessionFactory();
    private final Actions dao = Actions.getInstance();
    public static void main(String[] args) {
        TestClass test = new TestClass();
        test.dao.findByField(CarXML.class, test.sf, "engine_id", 1).stream()
                .forEach(car -> System.out.println(car.getId() + ": " + car.getDrivers()));



        /*DriverXML added = test.dao.findById(2, DriverXML.class, test.sf);
        driversList.add(added);
        test.dao.update(car4, test.sf);*/
        /*Driver driver2 = test.dao.findById(2, Driver.class, test.sf);
        driver2.getCars().forEach(System.out::println);
        driver2.getCars().remove(test.dao.findById(6, Car.class, test.sf));
        test.dao.update(driver2, test.sf);
        driver2.getCars().forEach(System.out::println);*/

        /*List<Engine> all = test.dao.findAll(Engine.class, test.sf);
        all.stream().forEach( eng -> System.out.println(eng.getCars()));*/

        /*test.dao.add(new Driver("Иванов"), test.sf);
        test.dao.add(new Driver("Петров"), test.sf);
        test.dao.add(new Driver("Сидоров"), test.sf);

        test.dao.add(new Car(Engine.of(1)), test.sf);
        test.dao.add(new Car(Engine.of(3)), test.sf);
        test.dao.add(new Car(Engine.of(2)), test.sf);
        test.dao.add(new Car(Engine.of(1)), test.sf);
        test.dao.add(new Car(Engine.of(3)), test.sf);*/

        /*List<Driver> drivers = test.dao.findAll(Driver.class, test.sf);
        drivers.stream().forEach(x -> System.out.println(x.getName() + " - " + x.getCars()));

        test.dao.delete(Engine.of(2), test.sf);

        Driver petrov = test.dao.findByField(Driver.class, test.sf, "name", "Петров");
        test.dao.delete(petrov, test.sf);

        drivers = test.dao.findAll(Driver.class, test.sf);
        drivers.stream().forEach(x -> System.out.println(x.getName() + " - " + x.getCars()));*/

        /*Driver petrov = test.dao.findByField(Driver.class, test.sf, "name", "Петров");

        petrov.getCars().add(test.dao.findById(3, Car.class, test.sf));
        test.dao.update(petrov, test.sf);

        List<Car> cars = test.dao.findAll(Car.class, test.sf);
        cars.stream().forEach(x -> System.out.println(x.getEngine().getId() + " - " + x.getDrivers()));*/
    }
}