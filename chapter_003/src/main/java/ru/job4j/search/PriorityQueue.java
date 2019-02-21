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
     * Метод, добавляющий задачу в зависимости от приоритета.
     * @param task задача
     */
    public void put(Task task) {
        int priority = task.getPriority();
        int i = 0;
        for (Task t : this.tasks) {
            if (priority < t.getPriority()) {
                break;
            }
            i++;
        }
        this.tasks.add(i, task);
    }

    /**
     * Метод, возвращающий первую задачу из списка.
     * @return task задача с наивысшим приоритетом.
     */
    public Task take() {
        return this.tasks.poll();
    }

    public LinkedList<Task> getTasks() {
        return this.tasks;
    }
}
