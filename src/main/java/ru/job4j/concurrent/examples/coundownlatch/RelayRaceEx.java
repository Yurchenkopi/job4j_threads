package ru.job4j.concurrent.examples.coundownlatch;

import java.util.concurrent.CountDownLatch;

public class RelayRaceEx {
    public static void main(String[] args) throws InterruptedException {
        int numberOfRunners = 4;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(numberOfRunners);

        for (int i = 1; i <= numberOfRunners; i++) {
            new Thread(new Runner(i, startLatch, finishLatch)).start();
        }
        System.out.println("Подготовка к старту!");
        Thread.sleep(2000);

        System.out.println("Старт!");
        startLatch.countDown();

        finishLatch.await();

        System.out.println("Забег завершен!");
    }

}

