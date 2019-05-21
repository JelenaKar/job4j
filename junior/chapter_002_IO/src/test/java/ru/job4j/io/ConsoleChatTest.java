package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleChatTest {

    public final static String LN = System.lineSeparator();

    public ConsoleChat chatting(String dialogs) {
        ConsoleChat chat = null;
        try (BufferedReader user = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(dialogs.getBytes())))) {
            chat = new ConsoleChat(user);
            chat.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chat;
    }

    /**
     * Тестирование завершение чата.
     */
    @Test
    public void whenSayFinishThenEndChatting() throws IOException {
        String words = "закончить";
        ConsoleChat chat = this.chatting(words);
        try (BufferedReader read = new BufferedReader(new FileReader(chat.getAddress() + "/chat.log"))) {
            assertThat(read.readLine(), is("Ты: закончить"));
        }
    }

    /**
     * Тестирование функций стоп и продолжить.
     */
    @Test
    public void whenSayStopAndProceedThenPausedAndContinue() throws IOException {
        String words = String.format("Hello%sстоп%sтест%sпродолжить%sтест%sзакончить", LN, LN, LN, LN, LN);
        ConsoleChat chat = this.chatting(words);
        List<String> buffer = chat.getBuffer();
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader(chat.getAddress() + "/chat.log"))) {
            assertThat(read.readLine(), is("Ты: Hello"));
            line = read.readLine();
            assertThat(line.substring(0, 5), is("Бот: "));
            assertThat(buffer.contains(line.substring(5)), is(true));
            assertThat(read.readLine(), is("Ты: стоп"));
            assertThat(read.readLine(), is("Ты: тест"));
            assertThat(read.readLine(), is("Ты: продолжить"));
            line = read.readLine();
            assertThat(line.substring(0, 5), is("Бот: "));
            assertThat(buffer.contains(line.substring(5)), is(true));
            assertThat(read.readLine(), is("Ты: тест"));
            line = read.readLine();
            assertThat(line.substring(0, 5), is("Бот: "));
            assertThat(buffer.contains(line.substring(5)), is(true));
            assertThat(read.readLine(), is("Ты: закончить"));
        }
    }

}