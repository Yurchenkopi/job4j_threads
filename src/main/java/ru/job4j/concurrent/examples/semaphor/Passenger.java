package ru.job4j.concurrent.examples.semaphor;

import java.util.concurrent.Semaphore;

public class Passenger implements Runnable {

    private static final int NUM_LIFTS = 2;
    private static final Semaphore AVAILABLE_LIFTS = new Semaphore(NUM_LIFTS, true);
    private final int passengerID;

    public Passenger(int passengerID) {
        this.passengerID = passengerID;
    }

    @Override
    public void run() {
        try {
            System.out.println("Пассажир " + passengerID + " ждет лифт...");
            AVAILABLE_LIFTS.acquire();
            System.out.println("Пассажир " + passengerID + " зашел в лифт");
            Thread.sleep(2000);
            System.out.println("Пассажир " + passengerID + " вышел из лифта");
            AVAILABLE_LIFTS.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
