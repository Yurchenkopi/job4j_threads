package ru.job4j.concurrent.examples.semaphor;

public class LiftSimulation {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread passenger = new Thread(new Passenger(i));
            passenger.start();
        }
    }
}


