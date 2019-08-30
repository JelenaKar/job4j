package ru.job4j.cash;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUITest {

    private final ByteArrayOutputStream virtualOut = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    public static final String LN = System.lineSeparator();

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.virtualOut));
    }

    @After
    public void loadSys() {
        System.setOut(this.standardOut);
    }

    /**
     * Тест запуска для несуществующей папки.
     */
    @Test
    public void whenStartForNonExistingFolderThenWarnAndExit() {
        String expected = "Папка wrongFolder не существует или пуста" + LN;
        StartUI.start("wrongFolder");
        assertThat(this.virtualOut.toString(), is(expected));
    }

    /**
     * Тест выгрузки несуществующего файла.
     */
    @Test
    public void whenSelectNonExistingFileThenWarnAndExit() throws IOException {
        String userInput = "nonexist" + LN + "n" + LN;
        String expected = "Список файлов в папке textfiles" + LN
                + "lorum.txt" + LN + "test.txt" + LN + "Радуга.txt" + LN
                + "Выберете имя файла для просмотра: " + "Файла с таким именем не существует" + LN
                + "Хотите просмотреть ещё один файл? (y/n): ";
        try (ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes())) {
            System.setIn(in);
            StartUI.start("textfiles");
            assertThat(this.virtualOut.toString(), is(expected));
        }
    }

    /**
     * Тест выгрузки файла из кеша, который предварительно был пуст.
     */
    @Test
    public void whenSelectExistingFileThenPrintIt() throws IOException {
        String userInput = "test.txt" + LN + "n" + LN;
        String expected = "Список файлов в папке textfiles" + LN
                + "lorum.txt" + LN + "test.txt" + LN + "Радуга.txt" + LN
                + "Выберете имя файла для просмотра: "
                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                + LN + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                + LN + "Хотите просмотреть ещё один файл? (y/n): ";
        try (ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes())) {
            System.setIn(in);
            StartUI.start("textfiles");
            assertThat(this.virtualOut.toString(), is(expected));
        }
    }
}