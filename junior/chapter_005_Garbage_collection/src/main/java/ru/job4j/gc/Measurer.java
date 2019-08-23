package ru.job4j.gc;

/**
 * Класс, в котором производятся все измерения.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Measurer {
    /**
     * Выпо
     */
    public static void info() {
        User user = new User("Иванов", "Иван", "Ростов-на-Дону", 30);
        TwoFieldClass tfc = new TwoFieldClass(new String[]{"Test", "Test2"}, 25);
        EmptyClass ec = new EmptyClass();
        System.out.printf("Размер объекта User с четырьмя полями: %d байт%s",
                (InstrumentationAgent.getObjectSize(user)), System.lineSeparator());
        System.out.printf("Размер объекта TwoFieldClass с двумя полями: %d байт%s",
                (InstrumentationAgent.getObjectSize(tfc)), System.lineSeparator());
        System.out.printf("Размер пустого объекта EmptyClass без полей: %d байт%s",
                InstrumentationAgent.getObjectSize(ec), System.lineSeparator());
        System.out.printf("Общий объем памяти: %.3f МБ%s",
                Runtime.getRuntime().totalMemory() / (1024.0 * 1024), System.lineSeparator());
        System.out.printf("Общий объем занятой памяти: %.3f МБ%s",
                (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024.0 * 1024), System.lineSeparator());
    }

    public static void main(String[] args) {
        System.out.println("Start");
        for (int i = 0; i < 10000; i++) {
            new User("name" + (i + 1), "surname" + (i + 1), "surname" + (i + 1), i + 10);
        }
        System.out.println("Finish");

//        Measurer.info();
    }
}
