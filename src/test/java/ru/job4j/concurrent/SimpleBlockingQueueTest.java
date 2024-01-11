package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

}