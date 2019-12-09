package ru.job4j.crud;

@FunctionalInterface
public interface ThrowableBiFunction<T, U, R, E extends Exception> {
    R apply(T var1, U var2) throws E;
}
