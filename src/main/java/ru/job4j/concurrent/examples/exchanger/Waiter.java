package ru.job4j.concurrent.examples.exchanger;

import java.util.concurrent.Exchanger;

public class Waiter implements Runnable {
    private final Exchanger<String> passDish;

    public Waiter(Exchanger<String> passDish) {
        this.passDish = passDish;
    }

    @Override
    public void run() {
        try {
            String dish = passDish.exchange(null);
            System.out.println("Waiter received: " + dish);
            System.out.println("Waiter served: " + dish);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
