package ru.job4j.concurrent.examples.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class HikingSimulation {
    public static void main(String[] args) {
        int numberOfTourists = 5;
        CyclicBarrier meetingPoint = new CyclicBarrier(numberOfTourists, () ->
                System.out.println("Все туристы встретились в базовом лагере. Продолжаем поход!"));

        for (int i = 1; i <= numberOfTourists; i++) {
            new Thread(new Tourist(i, meetingPoint)).start();
        }
    }
}
