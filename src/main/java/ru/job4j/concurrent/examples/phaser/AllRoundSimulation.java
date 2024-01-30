package ru.job4j.concurrent.examples.phaser;

import java.util.concurrent.Phaser;

public class AllRoundSimulation {
    public static void main(String[] args) {
        int numberOfParticipants = 3;
        Phaser phaser = new Phaser(numberOfParticipants);

        for (int i = 1; i <= numberOfParticipants; i++) {
            new Thread(new Sportsmen(i, phaser)).start();
        }
    }
}
