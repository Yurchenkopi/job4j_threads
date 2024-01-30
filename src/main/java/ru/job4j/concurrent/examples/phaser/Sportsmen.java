package ru.job4j.concurrent.examples.phaser;

import java.util.concurrent.Phaser;

public class Sportsmen implements Runnable {
    private final int participantNumber;
    private final Phaser phaser;

    public Sportsmen(int participantNumber, Phaser phaser) {
        this.participantNumber = participantNumber;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println("Участник " + participantNumber + " готовится к старту.");
        /* Фаза 1: Плавание */
        phaser.arriveAndAwaitAdvance();
        System.out.println("Участник " + participantNumber + " завершил плавание.");
        /* Фаза 2: Катание на велосипеде */
        phaser.arriveAndAwaitAdvance();
        System.out.println("Участник " + participantNumber + " завершил катание на велосипеде.");
        /* Фаза 3: Бег */
        phaser.arriveAndAwaitAdvance();
        System.out.println("Участник " + participantNumber + " завершил бег.");
        System.out.println("Участник " + participantNumber + " закончил все этапы соревнований.");
    }
}
