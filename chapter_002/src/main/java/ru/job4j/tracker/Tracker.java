package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс - хранилище данных заявок.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {

    private ArrayList<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    /**
     * Добавляет заявку в хранилище.
     * @param item объект Заявки.
     * @return объект внесённой заявки.
     */
    public Item add(Item item) {
        item.setId(this.generateId(item.getCreated()));
        this.items.add(item);
        return item;
    }

    /**
     * Заменяет заявку на заданную.
     * @param id уникальный идентификатор заявки в БД.
     * @param item объект изменённой Заявки.
     * @return true если вставка прошла успешно и false если объект с заданным id не найден.
     */
    public boolean replace(String id, Item item) {
        boolean result = true;
        int index = this.findPosById(id);
        if (index == -1) {
            result = false;
        } else {
            item.setId(id);
            this.items.set(index, item);
        }
        return result;
    }

    /**
     * Удаляет заявку с заданным id.
     * @param id уникальный идентификатор заявки в БД.
     * @return true если удаление прошло успешно и false если объект с заданным id не найден.
     */
    public boolean delete(String id) {
        int index = this.findPosById(id);
        boolean result = false;
        if (index != -1) {
            Item removed = this.items.remove(index);
            result = (removed != null);
        }
        return result;
    }

    /**
     * Выгружает из хранилища все непустые строки с заявками.
     * @return список всех объектов заявок.
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }

    /**
     * Выгружает из хранилища все заявки с заданным именем.
     * @return список объектов заявок, если такое имя существует, и null в противном случае.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = (ArrayList<Item>) this.items.stream()
                .filter(item -> item.getName().equals(key))
                .collect(Collectors.toList());
        if (result.size() == 0) {
            result = null;
        }
        return result;
    }

    /**
     * Выгружает из хранилища заявку с заданным id.
     * @return Объект заявки, если id существует, и null в противном случае.
     */
    public Item findById(String id) {
        Item result = null;
        int index = this.findPosById(id);
        if (index != -1) {
            result = this.items.get(index);
        }
        return result;
    }

    private int findPosById(String id) {
        return IntStream.range(0, this.items.size())
                .filter(i -> this.items.get(i).getId().equals(id))
                .findFirst().orElse(-1);
    }

    private String generateId(long created) {
        return String.valueOf(created + RN.nextInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tracker tracker = (Tracker) o;
        return items.equals(tracker.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}