package ru.job4j.concurrent.examples.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Tourist implements Runnable {
    private final int touristNumber;
    private final CyclicBarrier meetingPoint;

    public Tourist(int touristNumber, CyclicBarrier meetingPoint) {
        this.touristNumber = touristNumber;
        this.meetingPoint = meetingPoint;
    }

    @Override
    public void run() {
        try {
            System.out.println("Турист " + touristNumber + " отправился в поход.");
            Thread.sleep(2000);
            System.out.println("Турист " + touristNumber + " прибыл в базовый лагерь.");
            meetingPoint.await();
            System.out.println("Турист " + touristNumber + " продолжает поход.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
