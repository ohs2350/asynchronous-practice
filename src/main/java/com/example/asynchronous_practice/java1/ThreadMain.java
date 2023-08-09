package com.example.asynchronous_practice.java1;

import static java.lang.Thread.sleep;

public class ThreadMain {
    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        Runnable r2 = new Thread2();
        Thread thread2 = new Thread(r2);
        Thread daemon = new Thread(new MyDaemonThread());
        daemon.setDaemon(true);

        System.out.println("Thread: " + Thread.currentThread().getName());
        thread1.start();
        thread2.start();
        daemon.start();
    }


    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
            for (int i=0; i<10; i++) {
                System.out.println("thread 0: "+i);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
            for (int i=0; i<10; i++) {
                System.out.println("thread 1: "+i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class MyDaemonThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("daemon work");
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}