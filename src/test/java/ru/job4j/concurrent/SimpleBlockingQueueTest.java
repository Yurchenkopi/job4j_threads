package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    public void whenOfferAndPollThenVerify() throws InterruptedException {
        SimpleBlockingQueue<String> sbq = new SimpleBlockingQueue<>(4);
        List<String> rsl = new ArrayList<>();
        String first = "first";
        String second = "second";
        String third = "third";
        String fourth = "fourth";
        Thread consumer = new Thread(() -> {
            try {
                sbq.offer(first);
                sbq.offer(second);
                sbq.offer(third);
                sbq.offer(fourth);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread producer = new Thread(() -> {
            try {
                rsl.add(sbq.poll());
                rsl.add(sbq.poll());
                rsl.add(sbq.poll());
                rsl.add(sbq.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(rsl).containsExactly(first, second, third, fourth);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final int capacity = 10;
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(capacity);
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        (i) -> {
                            try {
                                queue.offer(i);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }

}