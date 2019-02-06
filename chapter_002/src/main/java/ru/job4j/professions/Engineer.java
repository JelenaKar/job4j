package ru.job4j.professions;

class House {

}

public class Engineer extends Profession {
    public House build() {
        House house = new House();
        return house;
    }
}
