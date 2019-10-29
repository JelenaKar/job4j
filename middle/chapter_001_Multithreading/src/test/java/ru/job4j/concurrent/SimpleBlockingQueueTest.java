package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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
    final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
    private final SimpleBlockingQueue<Integer> blockingQueue = new SimpleBlockingQueue<>(3);

    private final Thread producer = new Thread(() -> IntStream.range(0, 5).forEach(
            x -> {
                try {
                    blockingQueue.offer(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));


    private final Thread consumer = new Thread(() -> {
       while (blockingQueue.size() > 0 || !Thread.currentThread().isInterrupted()) {
            try {
                buffer.add(blockingQueue.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    });

    /**
     * Тестирование блокировки очереди для производителя в случае заполнения очереди.
     */
    @Test
    public void whenQueueOverflowsThenCapacityEqualsBoundAndProducerIsWaiting() throws InterruptedException {
        producer.start();
        producer.join(1000);
        assertThat(blockingQueue.size(), is(3));
        assertThat(producer.getState(), is(Thread.State.WAITING));
    }

    /**
     * Тестирование блокировки очереди для потребителя в случае пустой очереди.
     */
    @Test
    public void whenQueueISEmptyThenCapacityEqualsZeroAndConsumerIsWaiting() throws InterruptedException {
        consumer.start();
        consumer.join(1000);
        assertThat(blockingQueue.size(), is(0));
        assertThat(consumer.getState(), is(Thread.State.WAITING));
    }

    /**
     * Тестирование результатов асинхронной работы шаблона производитель-потребитель.
     */
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}