package ru.job4j.tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VirtualOutput implements Output {
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream stdout = System.out;

    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    public void backOutput() {
        System.setOut(this.stdout);
    }


    public void print(String string) {
        this.loadOutput();
        System.out.println(string);
        this.backOutput();
    }

    public ByteArrayOutputStream getOut() {
        return this.out;
    }
}