package ru.job4j.tracker;

import java.io.PrintStream;

public class ConsoleOutput implements Output {

    public void print(String string) {
        System.out.println(string);
    }
}
