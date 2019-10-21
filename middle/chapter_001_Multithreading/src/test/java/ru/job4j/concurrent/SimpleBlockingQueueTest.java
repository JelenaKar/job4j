package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    private final SimpleBlockingQueue<Integer> blockingQueue = new SimpleBlockingQueue<>(3);
    private final Runnable producer = () -> {
        for (int i = 1; i < 6; i++) {
            blockingQueue.offer(i);
        }
    };


    private final Runnable consumer = () -> {
        for (int i = 1; i < 6; i++) {
            blockingQueue.poll();
        }
    };

    /**
     * Тестирование блокировки очереди для производителя в случае заполнения очереди.
     */
    @Test
    public void whenQueueOverflowsThenCapacityEqualsBoundAndProducerIsWaiting() throws InterruptedException {
        Thread thread1 = new Thread(producer);
        thread1.start();
        thread1.join(1000);
        assertThat(blockingQueue.size(), is(3));
        assertThat(thread1.getState(), is(Thread.State.WAITING));
    }

    /**
     * Тестирование блокировки очереди для потребителя в случае пустой очереди.
     */
    @Test
    public void whenQueueISEmptyThenCapacityEqualsZeroAndConsumerIsWaiting() throws InterruptedException {
        Thread thread2 = new Thread(consumer, "Consumer1");
        thread2.start();
        thread2.join(1000);
        assertThat(blockingQueue.size(), is(0));
        assertThat(thread2.getState(), is(Thread.State.WAITING));
    }

    /**
     * Тестирование результатов асинхронной работы шаблона производитель-потребитель.
     */
    @Test
    public void whenProducerAndConsumerCompleteThenCapacityEqualsZeroAndThreadsAreTerminated() throws InterruptedException {
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(blockingQueue.size(), is(0));
        assertThat(thread1.getState(), is(Thread.State.TERMINATED));
        assertThat(thread2.getState(), is(Thread.State.TERMINATED));
    }
}