package ru.job4j.tracker;

import java.util.List;
import java.util.Random;

public interface ITracker {
    Random RN = new Random();
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
    default String generateId(long created) {
        return String.valueOf(created + RN.nextInt());
    }
}
