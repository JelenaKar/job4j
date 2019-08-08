package ru.job4j.srp;

import java.io.IOException;
import java.util.List;

/**
 * Класс-меню интерактивного калькулятора.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Menu {

    private InputOutput io;
    private InteractCalc calculator;
    private List<Action> menuList;
    private final static String LN = System.lineSeparator();

    public Menu(InputOutput io, InteractCalc calculator) {
        this.io = io;
        this.calculator = calculator;
        this.menuList = calculator.getOperations();
    }

    public void execute(int op) throws IOException {
        Double[] nums = io.askNumbers(this.getOperation(op - 1).getAmount(), calculator.getResult());
        this.getOperation(op - 1).getFunction().apply(nums);
        this.io.out.println("Результат равен: " + calculator.getResult());
        if (calculator.getResult() != 0) {
            this.io.out.print("Сохранить результат для следующей операции? (y/n) ");
            if (!"y".equals(this.io.in.readLine())) {
                calculator.eraseResult();
            }
        }
    }

    public Action getOperation(int i) {
        return this.menuList.get(i);
    }

    public int getActionsLength() {
        return this.menuList.size() + 1;
    }

    /**
     * Выводит стандартное меню в поток вывода.
     */
    public void show() {
        this.io.out.print(this.get());
    }

    /**
     * Выводит стандартное меню в поток вывода.
     */
    public String get() {
        StringBuilder menu = new StringBuilder("Простейший калькулятор:" + LN);
        int i = 1;
        for (Action elem : menuList) {
            menu.append(String.format("%d. %s", i++, elem.getName() + LN));
        }
        menu.append(String.format("%d. Выход%s", this.menuList.size() + 1, LN));
        return menu.toString();
    }

}
