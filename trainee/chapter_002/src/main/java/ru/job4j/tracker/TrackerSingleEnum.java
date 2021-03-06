package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;


/**
 * Класс Tracker Singleton при помощи enum - Eager loading.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
enum TrackerSingleEnum {
    INSTANCE;

    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Добавляет заявку в хранилище.
     * @param item объект Заявки.
     * @return объект внесённой заявки.
     */
    public Item add(Item item) {
        item.setId(this.generateId(item.getCreated()));
        this.items[this.position++] = item;
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
            this.items[index] = item;
            this.items[index].setId(id);
        }
        return result;
    }

    /**
     * Удаляет заявку с заданным id.
     * @param id уникальный идентификатор заявки в БД.
     * @return true если удаление прошло успешно и false если объект с заданным id не найден.
     */
    public boolean delete(String id) {
        boolean result = true;
        int index = this.findPosById(id);
        if (index == -1) {
            result = false;
        } else {
            System.arraycopy(this.items, index + 1, this.items, index, this.position - index - 1);
            this.items[this.position - 1] = null;
            this.position--;
        }
        return result;
    }

    /**
     * Выгружает из хранилища все непустые строки с заявками.
     * @return массив объектов заявок.
     */
    public Item[] findAll() {
        Item[] result = Arrays.copyOf(this.items, position);
        return result;
    }

    /**
     * Выгружает из хранилища все заявки с заданным именем.
     * @return массив объектов заявок, если такое имя существует, и null в противном случае.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[100];
        int index = 0;
        for (int i = 0; i < this.position; i++) {
            if (key.equals(this.items[i].getName())) {
                result[index] = this.items[i];
                index++;
            }
        }
        if (index > 0) {
            result = Arrays.copyOf(result, index);
        } else {
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
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
                break;
            }
        }
        return result;
    }

    private int findPosById(String id) {
        int result = -1;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    private String generateId(long created) {
        return String.valueOf(created + RN.nextInt());
    }

    public static void main(String[] args) {
        ITracker boo = TrackerSingleStaticField.getInstance();
        ITracker boo2 = TrackerSingleStaticField.getInstance();

        System.out.println(boo.getClass() + " i " + boo2.getClass());
    }
}
