package com.example.demo.configuration;

import lombok.SneakyThrows;

public class ThreadDemo implements Runnable{

    @Override
    public void run() {
        System.out.println("Test Thread!");
        try {
            Thread.sleep(3000);
            Thread.yield();
            System.out.println("Test Thread!");

            //this.wait();
            //this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadDemo thread = new ThreadDemo();
        thread.run();
    }
}
