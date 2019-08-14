package ru.job4j.isp;

import java.io.PrintStream;
import java.util.LinkedList;

/**
 * Абстрактный базовый класс выводимого и исполняемого пункта меню.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $
 * @since 0.1
 */
public abstract class MenuItemBase extends MenuItem implements Executable {

    protected String icon;
    public static String tabSymbol = "--";
    protected String tab;
    public static PrintStream out = System.out;

    public MenuItemBase(String name, String icon) {
        super(name);
        this.icon = icon;
        this.tab = tabSymbol;
    }

    public MenuItemBase(String name, LinkedList<MenuItem> children, String icon) {
        super(name, children);
        this.icon = icon;
        this.tab = tabSymbol;
        for (MenuItem child : this.children) {
            ((MenuItemBase) child).tab += this.tab;
        }
    }

    /**
     * Вывод произвольного пункта меню.
     */
    @Override
    public void print() {
        String listItem = (this.hasParent()) ? ((MenuItemBase) this.parent).tab : "";
        out.printf("%s%s %s%s", listItem, this.name, this.icon, System.lineSeparator());
    }
}
