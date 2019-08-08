package ru.job4j.srp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс-входная точка.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUI {

    private InputOutput io;
    private InteractCalc calculator;
    private Menu menu;

    /**
     * Конструтор инициализирующий поля.
     * @param io - экземпляр класса пользовательского ввода вывода.
     * @param calculator - экземпляр класса интерактивного калькулятора.
     */
    public StartUI(InputOutput io, InteractCalc calculator) {
        this.io = io;
        this.calculator = calculator;
        this.menu = new Menu(this.io, this.calculator);
    }

    public Menu getMenu() {
        return menu;
    }

    /**
     * Основой цикл программы.
     */
    public void start() throws IOException {
        int op;
        do {
            this.menu.show();
            List<Integer> range = new ArrayList<>();
            for (int i = 1; i <= this.menu.getActionsLength(); i++) {
                range.add(i);
            }
            op = io.askOperation(range);
            if (op != this.menu.getActionsLength()) {
                do {
                    this.menu.execute(op);
                } while ("y".equals(this.io.ask("Повторить операцию? (y/n) ")));
            }
        } while (op != (this.menu.getActionsLength()));
        this.io.in.close();
        this.io.out.close();
    }


    /**
     * Запуск программы.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        try {
            new StartUI(new InputOutput(System.in), new EngineerCalc()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}