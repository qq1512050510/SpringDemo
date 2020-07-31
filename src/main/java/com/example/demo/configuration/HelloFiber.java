package com.example.demo.configuration;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

public class HelloFiber {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int k = 0; k < 10000; k++) {
            Fiber<Void> fiber = new Fiber<Void>(new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    calc();
                }
            });
            fiber.start();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void calc() {
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                result += j;
            }
        }
        System.out.println();
    }
}
