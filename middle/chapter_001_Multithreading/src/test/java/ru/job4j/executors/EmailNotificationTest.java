package ru.job4j.executors;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class EmailNotificationTest {

    /**
     * Тестирование наличия открытого пула потоков.
     */
    @Test
    public void whenEmailNotificationIsCreatedThenIsShutdownIsFalse() {
        EmailNotification service = new EmailNotification();
        assertFalse(service.getPool().isShutdown());
    }

    /**
     * Тестирование статуса пула потоков после закрытия.
     */
    @Test
    public void whenEmailNotificationIsClosedThenIsShutdownIsTrue() {
        EmailNotification service = new EmailNotification();
        service.close();
        assertTrue(service.getPool().isShutdown());
    }

}