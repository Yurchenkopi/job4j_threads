package ru.job4j.concurrent.cash;

public class OptimisticException extends Exception {

    public OptimisticException(String message) {
        super(message);
    }
}
