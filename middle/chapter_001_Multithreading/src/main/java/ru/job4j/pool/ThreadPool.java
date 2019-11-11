package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Простейшая реализация пула потоков.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private volatile boolean isRunning = true;

    public ThreadPool(int... number) {
        if (number != null) {
            this.tasks = new SimpleBlockingQueue<>(number[0]);
        } else {
            this.tasks = new SimpleBlockingQueue<>();
        }
        IntStream.range(0, Runtime.getRuntime().availableProcessors())
                .forEach(i -> threads.add(new Thread(new TaskWorker())));
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) throws InterruptedException {
        if (isRunning) {
            this.tasks.offer(job);
        }
    }

    public void shutdown() {
        isRunning = false;
        threads.forEach(Thread::interrupt);
    }

    public boolean isShutdown() {
        return !isRunning;
    }

    public SimpleBlockingQueue<Runnable> getQueue() {
        return this.tasks;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable nextTask = tasks.poll();
                    nextTask.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }
}