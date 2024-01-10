package ru.job4j.concurrent.examples;

import java.util.concurrent.Semaphore;

public class SemaphoreEx {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println("Нить выполнила задачу");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
        Thread.sleep(3000);
        semaphore.release(1);
    }
}
