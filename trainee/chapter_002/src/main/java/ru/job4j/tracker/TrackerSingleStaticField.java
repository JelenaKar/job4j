package ru.job4j.tracker;

/**
 * Класс Tracker Singleton при помощи static поля instance - Lazy loading.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerSingleStaticField {
    private static ITracker instance;

    private TrackerSingleStaticField() {

    }

    public static ITracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }
}
