package ru.job4j.sort;

import java.util.Arrays;

/**
 * Класс подразделения.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Department {
    private String[] depCode;

    public Department(String[] depCode) {
        this.depCode = depCode;
    }

    public String[] getDepCode() {
        return depCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return Arrays.equals(depCode, that.depCode);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(depCode);
    }
}