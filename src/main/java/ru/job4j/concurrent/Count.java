package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }

    public static void main(String[] args) throws InterruptedException {
        Count c1 = new Count();

        Thread first = new Thread(c1::increment);
        Thread second = new Thread(c1::increment);
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(c1.get());
    }
}
