package ru.job4j.pseudo;

/**
 * Класс-стратегия, рисует треугольник.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Triangle implements Shape {

    /**
     * Реализует интерфейс рисовать.
     * @return изображение треугольника в виде строки.
     */
    @Override
    public String draw() {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        screen.append("   *").append(ln)
              .append("  * *").append(ln)
              .append(" *   *").append(ln)
              .append("*******");
        return screen.toString();
    }
}
