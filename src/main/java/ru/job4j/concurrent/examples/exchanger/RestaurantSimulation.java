package ru.job4j.concurrent.examples.exchanger;

import java.util.concurrent.Exchanger;

public class RestaurantSimulation {
    public static void main(String[] args) {
        Exchanger<String> passDish = new Exchanger<>();
        new Thread(new Chef(passDish)).start();
        new Thread(new Waiter(passDish)).start();
    }
}
