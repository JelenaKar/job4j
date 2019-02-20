package ru.job4j.search;

import java.util.LinkedList;

/**
 * Класс списка задач с приорететами.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (this.tasks.isEmpty()) {
            this.tasks.add(task);
        } else {
            int priority = task.getPriority();
            for (Task t : this.tasks) {
                if (priority < t.getPriority()) {
                    this.tasks.add(this.tasks.indexOf(t), task);
                    break;
                }
            }
        }
    }

    public Task take() {
        return this.tasks.peek();
    }
}
