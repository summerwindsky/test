package com.zj.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultThread {

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        testExecutor();
    }

    public static void testExecutor() {

        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.execute(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("int " + atomicInteger.getAndIncrement());
                    System.out.println(Thread.currentThread().getName() + ":===");
//                    if (atomicInteger.getAndIncrement() == 10) {
//                        break;
//                    }
                }
            }
        });

        executor.execute(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("int " + atomicInteger.getAndIncrement());
                    System.out.println(Thread.currentThread().getName() + "::::");
                    if (atomicInteger.getAndIncrement() == 20) {
                        break;
                    }
                }
            }
        });

    }
}
