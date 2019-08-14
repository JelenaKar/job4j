package ru.job4j.isp;

import java.util.LinkedList;

/**
 * Класс неактивного пункта меню.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public class MenuItemNonActive extends MenuItemBase {

    private static final String ICON = "❌";

    public MenuItemNonActive(String name) {
        super(name, ICON);
    }

    public MenuItemNonActive(String name, LinkedList<MenuItem> children) {
        super(name, children, ICON);
    }

    /**
     * Определяет действие при выборе неактивного пункта меню.
     */
    @Override
    public void execute() {
        out.println("Раздел в разработке");
    }
}
