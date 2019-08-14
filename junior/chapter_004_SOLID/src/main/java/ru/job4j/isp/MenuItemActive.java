package ru.job4j.isp;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Класс активного пункта меню.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class MenuItemActive extends MenuItemBase {

    private Consumer<String> action;
    private static final String ICON = "✔";

    public MenuItemActive(String name, Consumer<String> action) {
        super(name, ICON);
        this.action = action;
    }

    public MenuItemActive(String name, LinkedList<MenuItem> children, Consumer<String> action) {
        super(name, children, ICON);
        this.action = action;
    }

    /**
     * Определяет действие при выборе активного пункта меню.
     */
    @Override
    public void execute() {
        this.action.accept(this.name);
    }
}
