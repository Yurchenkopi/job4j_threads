package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {

                }
        );
        Thread second = new Thread(
                () -> {

                }
        );
        printThreadInfo(first, second);
        first.start();
        second.start();
        printThreadInfo(first, second);
        while (first.getState() != Thread.State.TERMINATED
        || second.getState() != Thread.State.TERMINATED) {
            printThreadInfo(first, second);
        }
        printThreadInfo(first, second);
        System.out.println("Работа завершена");
    }

    private static void printThreadInfo(Thread thr0, Thread thr1) {
        String name0 = thr0.getName();
        String name1 = thr1.getName();
        System.out.printf("Thread name: %s, thread state: %s%s",
                name0, thr0.getState(), System.lineSeparator());
        System.out.printf("Thread name: %s, thread state: %s%s",
                name1, thr1.getState(), System.lineSeparator());
    }

}
