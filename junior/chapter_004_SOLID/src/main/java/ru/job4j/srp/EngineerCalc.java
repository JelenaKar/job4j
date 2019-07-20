package ru.job4j.srp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Инженерный калькулятор - расширение простейшего интерактивного калькулятора.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class EngineerCalc extends InteractCalc {

    /**
     * Конструтор инициализирующий поля.
     *
     * @param in входной поток.
     */
    public EngineerCalc(InputStream in) {
        super(in);
    }

    /**
     * Рассчитать синус угла в радианах.
     * @param x - угол в радианах.
     */
    public void sin(double x) {
        this.result = Math.sin(x);
    }

    /**
     * Рассчитать косинус угла в радианах.
     * @param x - угол в радианах.
     */
    public void cos(double x) {
        this.result = Math.cos(x);
    }

    /**
     * Рассчитать тангенс угла в радианах.
     * @param x - угол в радианах.
     */
    public void tan(double x) {
        this.result = Math.tan(x);
    }

    /**
     * Рассчитать котангенс угла в радианах.
     * @param x - угол в радианах.
     */
    public void cot(double x) {
        this.result = 1.0 / Math.tan(x);
    }

    /**
     * Добавляет пункты расчёта тригонометрических функций.
     */
    @Override
    public void makeMenu() {
        super.makeMenu();
        this.menuList.add(new Menu("Рассчитать sin(x)", x -> {
            this.sin(x[0]);
            System.out.println(x[0]);
            return null;
        }, 1));

        this.menuList.add(new Menu("Рассчитать cos(x)", x -> {
            this.cos(x[0]);
            System.out.println(x[0]);
            return null;
        }, 1));

        this.menuList.add(new Menu("Рассчитать tan(x)", x -> {
            this.tan(x[0]);
            System.out.println(x[0]);
            return null;
        }, 1));

        this.menuList.add(new Menu("Рассчитать cot(x)", x -> {
            this.cot(x[0]);
            System.out.println(x[0]);
            return null;
        }, 1));
    }

    /**
     * Запуск программы.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        EngineerCalc ic = new EngineerCalc(System.in);
        try {
            ic.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
