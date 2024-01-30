package ru.job4j.concurrent.examples.coundownlatch;

import java.util.concurrent.CountDownLatch;

public class Runner implements Runnable {
    private final int runnerNumber;
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;

    public Runner(int runnerNumber, CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.runnerNumber = runnerNumber;
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            System.out.println("Бегун " + runnerNumber + " начал бежать.");

            Thread.sleep(2000);

            System.out.println("Бегун " + runnerNumber + " достиг финиша.");
            finishLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
