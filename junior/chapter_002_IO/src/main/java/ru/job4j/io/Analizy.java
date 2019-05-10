package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target, true))) {
            String line;
            boolean marked = false;
            while ((line = read.readLine()) != null) {
                if (line.startsWith("400") || line.startsWith("500")) {
                    if (!marked) {
                        writer.write(line, 4, line.length() - 4);
                        writer.write(";");
                        marked = true;
                    }
                } else {
                    if (marked) {
                        writer.write(line, 4, line.length() - 4);
                        writer.write(";\n");
                        marked = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy anal = new Analizy();
        anal.unavailable("server.log", "unavailable.csv");
    }
}
