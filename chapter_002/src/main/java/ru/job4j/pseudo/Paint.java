package ru.job4j.pseudo;

/**
 * Класс контекст, работающий со стратегиями через общий интерфейс.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Добавляет заявку в хранилище.
     * @param shape фигура - квадрат или треугольник.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint canvas = new Paint();
        System.out.println("Рисуем квадрат");
        canvas.draw(new Square());
        System.out.println("Рисуем треугольник");
        canvas.draw(new Triangle());
    }
}