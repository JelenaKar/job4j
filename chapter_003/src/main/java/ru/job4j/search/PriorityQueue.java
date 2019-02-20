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

    /**
     * Метод, возвращающий первую задачу из списка.
     * @return task задача с наивысшим приоритетом.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
