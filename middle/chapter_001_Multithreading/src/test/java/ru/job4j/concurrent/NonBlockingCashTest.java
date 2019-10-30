package ru.job4j.concurrent;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class NonBlockingCashTest {

    public NonBlockingCash cash = new NonBlockingCash();

    @Before
    public void addElements() {
        cash.add(new Base(1, "Vasya"));
        cash.add(new Base(2, "Vanya"));
        cash.add(new Base(3, "Kostya"));
    }

    /**
     * Тестирование конкуррентного добавления нового значения.
     */
    @Test
    public void whenConcurrentAdditionThenThrowsOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread th1 = new Thread(
                () -> {
                    try {
                        Base mod1 = new Base(4, "Petya");
                        cash.add(mod1);
                    }  catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    try {
                        Base mod1 = new Base(4, "Kolya");
                        cash.add(mod1);
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(ex.get().getMessage(), is("Model with this id is already exists."));
    }

    /**
     * Тестирование конкуррентной модификации значения из кэша.
     */
    @Test
    public void whenConcurrentModificationThenThrowsOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread th1 = new Thread(
                () -> {
                    try {
                        Base mod1 = new Base(1, "Petya", cash.get(1).getVersion());
                        Thread.sleep(1000);
                        cash.update(mod1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    try {
                        Base mod1 = new Base(1, "Kolya", cash.get(1).getVersion());
                        Thread.sleep(1000);
                        cash.update(mod1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(ex.get().getMessage(), is("Concurrent modification of model meaning."));
    }

    /**
     * Тестирование последовательной модификации значения из кэша.
     */
    @Test
    public void whenSerialModificationThenReturnTrueGetLast() throws InterruptedException {
        Thread th1 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        Base mod1 = new Base(2, "Petya", cash.get(2).getVersion());
                        assertTrue(cash.update(mod1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    Base mod1 = new Base(2, "Kolya", cash.get(2).getVersion());
                    cash.update(mod1);
                }
        );
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(cash.get(2).getName(), is("Petya"));
    }

    /**
     * Тестирование конкуррентного изменения и удаления значения из кэша.
     */
    @Test
    public void whenConcurrentDeletionThenThrowsOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread th1 = new Thread(
                () -> {
                    try {
                        Base mod1 = new Base(3, "Petya", cash.get(1).getVersion());
                        Thread.sleep(1000);
                        cash.update(mod1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
        Thread th2 = new Thread(
                () -> {
                    try {
                        Base mod1 = new Base(cash.get(3));
                        Thread.sleep(2000);
                        cash.delete(mod1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (OptimisticException oe) {
                        ex.set(oe);
                    }
                }
        );
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        assertThat(ex.get().getMessage(), is("Concurrent modification of model meaning."));
    }

    /**
     * Тестирование последовательного изменения и удаления значения из кэша.
     */
    @Test
    public void whenSerialDeletionThenReturnTrueAndGetNull() throws InterruptedException {
        Thread th1 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        Base mod1 = new Base(cash.get(3));
                        assertTrue(cash.delete(mod1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread th2 = new Thread(
                () -> {
                    Base mod1 = new Base(3, "Kolya", cash.get(2).getVersion());
                    cash.update(mod1);
                }
        );
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        Base expect = null;
        assertThat(cash.get(3), is(expect));
    }
}