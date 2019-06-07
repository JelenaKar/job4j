package ru.job4j.exam;

import java.util.*;

/**
 * Класс, обрабатывающий зависимости между скриптами.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Dependencies {

    /**
     * Метод, возвращающий список номеров всех скриптов, требующихся для запуска заданного.
     * @param dependencies - множество скриптов с указанием их зависимосей.
     * @param scriptId - скрипт, который необходимо запустить.
     * @return список скриптов, необходимых для запуска.
     */
    public static List<Integer> load(Map<Integer, List<Integer>> dependencies, Integer scriptId) {
        Set<Integer> res = new HashSet<>(Set.of(scriptId));
        Queue<Integer> set = new LinkedList<>(List.of(scriptId));
        List<Integer> temp;
        while (!set.isEmpty()) {
            temp = dependencies.get(set.poll());
            res.addAll(temp);
            set.addAll(temp);
        }
        return new ArrayList<>(res);
    }
}
