package ru.job4j.tracker;

/**
 * Класс Tracker Singleton при помощи static final поля instance - Eager loading.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerSingleStaticFinalField {
    private static final ITracker INSTANCE = new Tracker();

    private TrackerSingleStaticFinalField() {

    }

    public static ITracker getInstance() {
        return INSTANCE;
    }
}
