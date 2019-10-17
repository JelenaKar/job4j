package ru.job4j.demonstration;

/**
 * Класс, демонстрирующий проблемы многопоточности.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Problems {

    public static class TestClass {
        public /*volatile*/ String greetings = "Hello";
        public int i = 0;

        public void changeGreetings(String greetings) {
            this.greetings = greetings;
        }

        public void increment() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

        public void decrement() {
            i--;
        }

        public int getValue() {
            return i;
        }
    }

    /**
     * Демонстрирует проблему видимости.
     */
    public void visibilityProblem() {
        TestClass obj = new TestClass();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                obj.changeGreetings("Hi");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(obj.greetings);
        });

        t1.start();
        t2.start();

    }

    /**
     * Демонстрирует проблему состояния гонки (race condition).
     */
    public void raceCondition() {
        TestClass obj = new TestClass();
        Runnable task = () -> {
            //synchronized (demo) {
                obj.increment();
                System.out.printf("Value for %s after increment = %d%s",
                        Thread.currentThread().getName(),
                        obj.getValue(),
                        System.lineSeparator());
                obj.decrement();
            System.out.printf("Value for %s after decrement = %d%s",
                    Thread.currentThread().getName(),
                    obj.getValue(),
                    System.lineSeparator());
            //}
        };
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        Problems demo = new Problems();
        demo.visibilityProblem();
        demo.raceCondition();
    }

}
