package ru.job4j.tracker;


/**
 * Класс Tracker Singleton при помощи static final class Holder - Lazy loading.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerSingleStaticFinalClass {

    private TrackerSingleStaticFinalClass() {

    }

    public static final class Holder {
        private static final Tracker INSTANCE = new Tracker();
    }

    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }
}
