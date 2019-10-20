package ru.job4j.demonstration;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CounterTest {

    private class ThreadCounter extends Thread {
        private final Counter counter;

        public ThreadCounter(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            this.counter.increment();
        }
    }

    @Test
    public void whenExecute2ThreadsThenGrows2() throws InterruptedException {
        Counter counter = new Counter();
        ThreadCounter thread1 = new ThreadCounter(counter);
        ThreadCounter thread2 = new ThreadCounter(counter);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        assertThat(counter.get(), is(2));
    }

}