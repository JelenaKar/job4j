package ru.job4j.generic;

public class RoleStore<T extends Role> extends AbstractStore<T> {

    protected RoleStore(int size) {
        super(size);
    }
}
