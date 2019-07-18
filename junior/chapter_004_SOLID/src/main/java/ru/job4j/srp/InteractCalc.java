package ru.job4j.srp;

import ru.job4j.calculator.Calculator;

import java.io.*;

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

    /**
     * Конструтор инициализирующий поля.
     * @param in входной поток.
     */
    public InteractCalc(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
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
                double[] nums;
                if (op != 5) {
                    do {
                        nums = this.askNumbers();
                        this.showResult(nums[0], nums[1], op);
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
        } while (op != 5);
        this.in.close();
        this.out.close();
    }

    /**
     * Распечатать результаты вычисления.
     * @param first первое вещественное число.
     * @param second второе вещественное число.
     * @param action номер выбранного пункта меню.
     */
    private void showResult(Double first, Double second, int action) {
        switch (action) {
            case 1:
                this.add(first, second);
                break;
            case 2:
                this.subtract(first, second);
                break;
            case 3:
                this.multiple(first, second);
                break;
            case 4:
                this.div(first, second);
                break;
            case 5:
                break;
            default:
        }
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
        if (d < 1 || d > 5) {
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
     * Возвращает стандартное меню в виде строки.
     * @return меню.
     */
    public String getMenu() {
        final String LN = System.lineSeparator();
        String menu = "Простейший калькулятор:" + LN
            + "1. Сложение" + LN
            + "2. Вычитание" + LN
            + "3. Умножение" + LN
            + "4. Деление" + LN
            + "5. Выход" + LN
            + "Выберите операцию: ";
        return menu;
    }

    /**
     * Запрашивает ввод вещественных чисел у пользователя.
     * @return 2 вещественных числа в виде массива.
     */
    private double[] askNumbers() throws IOException {
        double[] res = new double[2];
        double first = this.getResult();
        int i = 0;
        if (first != 0) {
            res[i++] = first;
        }
        do {
            this.out.printf("Введите %d-е число: ", i + 1);
            String userNum = in.readLine();
            if (this.isDouble(userNum)) {
                res[i++] = Double.valueOf(userNum);
            }

        } while (i != 2);
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
