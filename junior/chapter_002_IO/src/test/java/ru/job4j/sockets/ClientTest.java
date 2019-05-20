package ru.job4j.sockets;

import org.junit.Test;

import java.io.*;
import java.net.*;

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
public class ClientTest {

    private static final String LN = System.lineSeparator();

    /**
     * Тестирование получения ответа от сервера и отправки серверу.
     */
    @Test
    public void whenTypeInConsoleEndPressEnterThenSendToServer() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String answers = String.format("1) test%sanswer%s%s2) test answer%s%s3) test%sanswer%srows%s%s",
                LN, LN, LN, LN, LN, LN, LN, LN, LN);
        ByteArrayInputStream in = new ByteArrayInputStream(answers.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        String dialogs = String.format("Привет!%sСколько тебе лет?%sНу пока!%s", LN, LN, LN);
        try (BufferedReader console = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(dialogs.getBytes())))) {
            Client user = new Client(socket, console);
            user.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(String.format("Привет!%sСколько тебе лет?%sНу пока!%s", LN, LN, LN)));
    }

}