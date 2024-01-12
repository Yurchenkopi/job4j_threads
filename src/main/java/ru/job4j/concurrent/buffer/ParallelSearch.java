package ru.job4j.concurrent.buffer;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {

        final int capacity = 10;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(capacity);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());

                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        final Thread produced = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );
        produced.start();
        produced.join();
        while (consumer.isAlive()) {
            if (consumer.getState() == Thread.State.WAITING) {
                consumer.interrupt();
            }
        }
    }
}
