package ru.job4j.crud;

@FunctionalInterface
public interface ThrowableFunction<T, R, E extends Exception> {
    R apply(T var1) throws E;
}
