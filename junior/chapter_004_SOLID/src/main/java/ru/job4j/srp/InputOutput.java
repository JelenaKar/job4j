package ru.job4j.srp;

import java.io.*;
import java.util.List;

/**
 * Класс ввода/вывода
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class InputOutput {

    public BufferedReader in;
    public PrintStream out = System.out;
    private final static String LN = System.lineSeparator();

    /**
     * Конструтор инициализирующий поток ввода при стандартом выводе.
     * @param in входной поток.
     */
    public InputOutput(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }

    /**
     * Конструтор инициализирующий потоки ввода и вывода.
     * @param in входной поток.
     * @param out выходной поток.
     */
    public InputOutput(InputStream in, PrintStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    /**
     * Выводит на экран вопрос, принимает введённый ответ.
     * @param ask выводимый на экран вопрос.
     * @return введённый пользователем ответ.
     */
    public String ask(String ask) throws IOException {
        this.out.print(ask);
        return this.in.readLine();
    }

    /**
     * Выводит на экран запрос операции, принимает введённый ответ.
     * @param range диапазон возможных пунктов меню.
     * @return введённый пользователем ответ.
     */
    public int askOperation(List<Integer> range) throws IOException {
        int i = -1;
        int key = -1;
        do {
            try {
                int tmp = Integer.valueOf(this.ask("Выберите операцию: "));
                i = (int) range.stream().filter(value -> value == tmp).count();
                if (i <= 0) {
                    this.out.printf("Операция должна быть целым числом в интервале 1-%d!%s", range.size(), LN);
                }
                key = tmp;
            } catch (NumberFormatException | NullPointerException nfe) {
                this.out.printf("Операция должна быть целым числом в интервале 1-%d!%s", range.size(), LN);
            }
        } while (i <= 0);
        return key;
    }

    /**
     * Запрашивает ввод вещественных чисел у пользователя.
     * @param amount - количество аргументов для вычисления.
     * @param calculated - ранее полученный результат.
     * @return вещественные числа в виде массива.
     */
    public Double[] askNumbers(int amount, Double calculated) throws IOException {
        Double[] res = new Double[amount];
        int i = 0;
        if (calculated != 0) {
            res[i++] = calculated;
        }
        while (i != amount) {
            String userNum = this.ask(String.format("Введите %d-е число: ", i + 1));
            if (this.isDouble(userNum)) {
                res[i++] = Double.valueOf(userNum);
            }

        }
        return res;
    }

    /**
     * Проверить является ли значение вещественным числом.
     * @param strNum значение, введённое в консоль пользователем.
     * @return true - если число вещественное, false - в противном случае.
     */
    private boolean isDouble(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
