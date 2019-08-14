package ru.job4j.isp;

import java.util.LinkedList;

/**
 * Класс меню
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Menu implements Printable {

    private LinkedList<MenuItem> items;

    public Menu(LinkedList<MenuItem> items) {
        this.items = items;
    }

    /**
     * Выводит итоговое меню в виде дерева.
     */
    @Override
    public void print() {
        LinkedList<MenuItem> storage = new LinkedList<>();
        for (MenuItem item : items) {
            storage.push(item);
            while (!storage.isEmpty()) {
                MenuItem tmp = storage.pop();
                tmp.print();
                LinkedList<MenuItem> children = tmp.getChildren();
                if (!children.isEmpty()) {
                    int len = children.size();
                    for (int i = 0; i < len; i++) {
                        storage.push(children.pollLast());
                    }
                }
            }
        }
    }
}
