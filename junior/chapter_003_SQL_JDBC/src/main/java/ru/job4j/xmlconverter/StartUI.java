package ru.job4j.xmlconverter;

import java.io.*;
import java.util.Scanner;

/**
 * Класс-приложение.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUI {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private PrintStream out = System.out;

    public StartUI() {

    }

    public StartUI(BufferedReader in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Начальный метод приложения.
     */
    public void start() {
        try (Scanner in = new Scanner(this.in)) {
            int number;
            this.out.print("Введите целое положительное число: ");
            do {
                if (!in.hasNextInt()) {
                    this.out.print("Ошибка ввода! Введите целое положительное число: ");
                    in.next();
                }
                number = in.nextInt();
                if (number <= 0) {
                    this.out.print("Ошибка ввода! Введите целое положительное число: ");
                }
            } while (number <= 0);
            try (StoreSQL sql = new StoreSQL(new Config())) {
                sql.generate(number);
                File scheme = new File(getClass().getClassLoader().getResource("transform.xsl").getPath());
                File source = new File(scheme.getParent() + "/temp.xml");
                File dest = new File(scheme.getParent() + "/output.xml");

                new StoreXML(source).save();

                int sum = new ParseXML(ConvertXSQT.convert(source, dest, scheme)).sumOfAllElements();
                this.out.printf("Сумма всех элементов: %d", sum);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new StartUI().start();
    }
}
