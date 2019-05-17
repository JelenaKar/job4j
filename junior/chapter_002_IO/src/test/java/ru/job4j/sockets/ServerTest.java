package ru.job4j.sockets;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {

    private static final String LN = System.lineSeparator();
    private static final String BYE = String.format("Пока!%sНадеюсь на скорую встречу :)%s%s", LN, LN, LN);

    public static void testServer(String question, String answer) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(question.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);

        Server server = new Server(socket);
        server.connect();

        assertThat(out.toString(), is(answer));
    }

    /**
     * Тестирование выхода при прощании.
     */
    @Test
    public void whenUserSaysGoodbyethenByeBack() throws IOException {
        testServer("Ну пока!", BYE);
    }

    /**
     * Тестирование приветствия.
     */
    @Test
    public void whenUserSaysHellothenBackGreeting() throws IOException {
        testServer("Здравствуй!\nПока!", String.format("Привет, дорогой друг! Я всемогущий оракл!%s%s%s", LN, LN, BYE));
    }

    /**
     * Тестирование вопроса про возраст.
     */
    @Test
    public void whenUserAskAgethenSay100() throws IOException {
        testServer("Сколько тебе лет?\nПока!", String.format("Мне 100 лет!%s%s%s", LN, LN, BYE));
    }

    /**
     * Тестирование вопроса про имя.
     */
    @Test
    public void whenUserAskNamethenSayVasya() throws IOException {
        testServer("Как тебя звать?\nПока!", String.format("Меня зовут Вася!\nА тебя?%s%s%s", LN, LN, BYE));
    }

}