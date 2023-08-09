package com.example.asynchronous_practice.java5;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
    @Test
    void schedule() throws InterruptedException {
        Runnable runnable = () -> System.out.println("Thread: " + Thread.currentThread().getName());
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(runnable, 1, TimeUnit.SECONDS);
        System.out.println("This sentence is printed first.");

        Thread.sleep(2000L);
        executorService.shutdown();
    }

    @Test
    void scheduleAtFixedRate() throws InterruptedException {
        Runnable runnable = () -> { // 1초가 소요되는 작업
            System.out.println("Start scheduleAtFixedRate:    " + LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish scheduleAtFixedRate:    " + LocalTime.now());
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // n초 마다 작업 수행
        executorService.scheduleAtFixedRate(runnable, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(10000L);
        executorService.shutdown();
    }


    @Test
    void scheduleWithFixedDelay() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Start scheduleWithFixedDelay:    " + LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish scheduleWithFixedDelay:    " + LocalTime.now());
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // 작업 완료되고 n초 후 작업 수행
        executorService.scheduleWithFixedDelay(runnable, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(10000L);
        executorService.shutdown();
    }
}
