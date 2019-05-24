package ru.job4j.nio;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class FindFileTest {

    private final static String FOLDER = "src/main/java/io/tmpdir/";
    private final static String USAGE = System.lineSeparator() + "Usage";
    private void testExceptions(String[] args, String expected) throws IOException {
        try (ByteArrayOutputStream mem = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(mem));
            FindFile.search(args);
            assertThat(mem.toString().startsWith(expected), is(true));
        }
    }

    /**
     * Тест ситуации, когда не указана корневая папка поиска.
     */
    @Test
    public void whenSrcDirectoryIsNotSetThenExceptionMessage() throws IOException {
        String expected = String.format("The following option is required: [-d]%s", USAGE);
        this.testExceptions(new String[] {"-f", "-n", "file"}, expected);
    }

    /**
     * Тест ситуации, когда не указано имя файла.
     */
    @Test
    public void whenNameIsNotSetThenExceptionMessage() throws IOException {
        String expected = String.format("The following option is required: [-n]%s", USAGE);
        this.testExceptions(new String[] {"-f", "-d", "/"}, expected);
    }

    /**
     * Тест ситуации, когда не указан критерий поиска (полное имя / маска).
     */
    @Test
    public void whenSearchTypeIsNotSetThenExceptionMessage() throws IOException {
        String expected = String.format("Main parameters are required (\"-f - искать по полному имени файла; -m - искать по маске\")%s", USAGE);
        this.testExceptions(new String[] {"-n", "file", "-d", "/"}, expected);
    }

    /**
     * Тест ситуации, когда корневой папки поиска не существует.
     */
    @Test
    public void whenSrcDirectoryIsNotFoundThenExceptionMessage() throws IOException {
        String expected = String.format("Папки с именем /err не существует!%s", USAGE);
        this.testExceptions(new String[] {"-n", "file", "-d", "/err", "-f"}, expected);
    }

    /**
     * Тест ситуации, когда папки для размещения результата не существует.
     */
    @Test
    public void whenTargetDirectoryIsNotFoundThenExceptionMessage() throws IOException {
        String expected = String.format("Папки с именем /err не существует!%s", USAGE);
        this.testExceptions(new String[] {"-n", "file", "-d", "/", "-o", "/err/res.log", "-f"}, expected);
    }

    /**
     * Если не указан файл для печати результата, то вывод на консоль.
     */
    @Test
    public void whenTargetIsNotSetThenConsoleOutput() throws IOException {
        try (ByteArrayOutputStream mem = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(mem));
            String[] args = {"-f", "-n", "file1", "-d", FOLDER};
            FindFile.search(args);
            assertThat(mem.toString(), is(FOLDER + "file1" + System.lineSeparator()));
        }
    }

    /**
     * Если файлов по критериям не найдено, и файла для вывода не указано, то в консоли пустота.
     */
    @Test
    public void whenFileIsNotFoundThenEmpty() throws IOException {
        try (ByteArrayOutputStream mem = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(mem));
            String[] args = {"-f", "-n", "file1*", "-d", FOLDER};
            FindFile.search(args);
            assertThat(mem.toString(), is(""));
        }
    }

    /**
     * Если ищем по полному имени файла, то в файле результата одна строка.
     */
    @Test
    public void whenFindWithFullnameAndTargetThenWritesTwoRowsIntoFile() throws IOException {
        String[] args = {"-f", "-n", "file.txt", "-d", FOLDER, "-o", FOLDER + "result.log"};
        FindFile.search(args);
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "result.log"))) {
            reader.lines().forEach(line -> result.add(line));
        }
        assertThat(result.size(), is(1));
        assertThat(result.contains(FOLDER + "dir1/dir3/file.txt"), is(true));
    }

    /**
     * Если ищем по маске, то в файле результата четыре строки.
     */
    @Test
    public void whenFindWithMaskAndTargetThenWritesFourRowsIntoFile() throws IOException {
        String[] args = {"-m", "-n", "file*.txt", "-d", FOLDER, "-o", FOLDER + "result.log"};
        FindFile.search(args);
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "result.log"))) {
            reader.lines().forEach(line -> result.add(line));
        }
        assertThat(result.size(), is(4));
        assertThat(result.contains(FOLDER + "file1.txt"), is(true));
        assertThat(result.contains(FOLDER + "file101.txt"), is(true));
        assertThat(result.contains(FOLDER + "dir1/file3.txt"), is(true));
        assertThat(result.contains(FOLDER + "dir1/dir3/file.txt"), is(true));
    }

}