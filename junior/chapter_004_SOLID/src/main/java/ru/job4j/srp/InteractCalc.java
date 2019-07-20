package ru.job4j.srp;

import ru.job4j.calculator.Calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Интерактивный калькулятор - оболочка над классом Calculator.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class InteractCalc extends Calculator {

    private BufferedReader in;
    private PrintStream out = System.out;
    protected List<Menu> menuList = new ArrayList<>();
    protected final static String LN = System.lineSeparator();

    /**
     * Конструтор инициализирующий поля.
     * @param in входной поток.
     */
    public InteractCalc(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.makeMenu();
    }

    /**
     * Основой цикл программы.
     */
    public void start() throws IOException {
        int op = 0;
        do {
            this.out.print(this.getMenu());
            String answer = in.readLine();
            if (!this.isSuitable(answer)) {
                this.out.println("Операция должна быть целым числом в интервале 1-5!");
            } else {
                op = Integer.parseInt(answer);
                Double[] nums;
                if (op != (this.menuList.size() + 1)) {
                    do {
                        nums = this.askNumbers(this.menuList.get(op - 1).getAmount());
                        this.showResult(nums, op);
                        if (this.getResult() != 0) {
                            this.out.print("Сохранить результат для следующей операции? (y/n) ");
                            if (!"y".equals(in.readLine())) {
                                this.eraseResult();
                            }
                        }
                        this.out.print("Повторить операцию? (y/n) ");
                    } while ("y".equals(in.readLine()));
                }
            }
        } while (op != (this.menuList.size() + 1));
        this.in.close();
        this.out.close();
    }

    /**
     * Распечатать результаты вычисления.
     * @param nums - входящий массив вещественных чисел.
     * @param action номер выбранного пункта меню.
     */
    private void showResult(Double[] nums, int action) {
        this.menuList.get(action - 1).getAction().apply(nums);
        this.out.println("Результат равен: " + this.getResult());
    }

    /**
     * Проверить введённый пункт меню на корректность.
     * @param strNum пункт меню, введённый в консоль пользователем.
     */
    private boolean isSuitable(String strNum) {
        int d;
        boolean res;
        try {
            d = Integer.parseInt(strNum);
            res = true;
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        if (d < 1 || d > (this.menuList.size() + 1)) {
            res = false;
        }
        return res;
    }

    /**
     * Проверить является ли значение вещественным числом.
     * @param strNum значение, введённое в консоль пользователем.
     */
    private boolean isDouble(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Создаёт меню из списка.
     */
    public void makeMenu() {
        this.menuList.addAll(List.of(
                new Menu("Сложение", x -> {
                    this.add(x[0], x[1]);
                    return null;
                }, 2),
                new Menu("Вычитание", x -> {
                    this.subtract(x[0], x[1]);
                    return null;
                }, 2),
                new Menu("Умножение", x -> {
                    this.multiple(x[0], x[1]);
                    return null;
                }, 2),
                new Menu("Деление", x -> {
                    this.div(x[0], x[1]);
                    return null;
                }, 2)
        ));
    }

    /**
     * Возвращает стандартное меню в виде строки.
     * @return меню.
     */
    public String getMenu() {
        String menu = "Простейший калькулятор:" + LN;
        int i = 1;
        for (Menu elem : menuList) {
            menu += String.format("%d. %s", i++, elem.getName() + LN);
        }
        menu += String.format("%d. Выход%s", this.menuList.size() + 1, LN);
        menu += "Выберите операцию: ";
        return menu;
    }

    /**
     * Запрашивает ввод вещественных чисел у пользователя.
     * @return 2 вещественных числа в виде массива.
     */
    private Double[] askNumbers(int amount) throws IOException {
        Double[] res = new Double[amount];
        Double first = this.getResult();
        int i = 0;
        if (first != 0) {
            res[i++] = first;
        }
        while (i != amount) {
            this.out.printf("Введите %d-е число: ", i + 1);
            String userNum = in.readLine();
            if (this.isDouble(userNum)) {
                res[i++] = Double.valueOf(userNum);
            }

        }
        return res;
    }

    /**
     * Заменить поток вывода.
     */
    public void changeOutput(PrintStream out) {
        this.out = out;
    }

    /**
     * Запуск программы.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        InteractCalc ic = new InteractCalc(System.in);
        try {
            ic.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
