package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final int size = Runtime.getRuntime().availableProcessors();

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(final int capacity) {
        tasks = new SimpleBlockingQueue<>(capacity);
        for (int i = 0; i < size; i++) {
            System.out.println(i);
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5);
        for (int i = 0; i < 8; i++) {
            int tempI = i;
            threadPool.work(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.printf("Current thread: %s, i = %d; j = %d.%s",
                            Thread.currentThread().getName(), tempI, j, System.lineSeparator());
                }
            });
        }
        Thread.sleep(5000);
        threadPool.shutdown();
    }
}
