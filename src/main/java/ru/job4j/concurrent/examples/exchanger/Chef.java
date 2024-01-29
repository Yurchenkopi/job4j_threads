package ru.job4j.concurrent.examples.exchanger;

import java.util.concurrent.Exchanger;

public class Chef implements Runnable {
    private final Exchanger<String> passDish;

    public Chef(Exchanger<String> passDish) {
        this.passDish = passDish;
    }

    @Override
    public void run() {
        try {
            String dish = "Steak";
            System.out.println("Chef prepared: " + dish);
            passDish.exchange(dish);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
