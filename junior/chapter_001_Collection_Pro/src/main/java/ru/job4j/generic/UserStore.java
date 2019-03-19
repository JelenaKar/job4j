package ru.job4j.generic;

public class UserStore<T extends User> extends AbstractStore<T> {

    protected UserStore(int size) {
        super(size);
    }
}
