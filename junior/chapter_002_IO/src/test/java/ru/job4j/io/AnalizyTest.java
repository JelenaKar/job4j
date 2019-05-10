package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenServerIsUnavailable() {
        String directory = System.getProperty("user.dir") + "/src/main/resources/";
        Analizy analyze = new Analizy();
        String source = directory + "server.log";
        String target = directory + "unavailable.csv";
        analyze.unavailable(source, target);
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02;"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}