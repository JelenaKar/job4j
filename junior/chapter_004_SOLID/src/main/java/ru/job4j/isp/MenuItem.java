package ru.job4j.isp;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Класс абстрактного выводимого меню.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public abstract class MenuItem implements Printable {

    protected String name;
    protected LinkedList<MenuItem> children = new LinkedList<>();
    protected MenuItem parent;

    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem(String name, LinkedList<MenuItem> children) {
        this.name = name;
        this.children = children;
        for (MenuItem child : this.children) {
            child.setParent(this);
        }
    }

    public void addChild(MenuItem child) {
        this.children.add(child);
        this.children.getLast().setParent(this);
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public String getName() {
        return name;
    }

    public LinkedList<MenuItem> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return name.equals(menuItem.name) && Objects.equals(parent, menuItem.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
