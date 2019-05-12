package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

private List<String> cash = new ArrayList<>();

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String line;
            boolean marked = false;
            while ((line = read.readLine()) != null) {
                if (line.startsWith("400") || line.startsWith("500")) {
                    if (!marked) {
                        cash.add(line.substring(4) + ";");
                        marked = true;
                    }
                } else {
                    if (marked) {
                        cash.add(line.substring(4) + ";\n");
                        marked = false;
                    }
                }
            }
            writeAll(cash, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeAll(List<String> cash, String target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target, true))) {
            for (String line : cash) {
                writer.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
