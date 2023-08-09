package com.example.asynchronous_practice.java5;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTest {

    @Test
    public void executorRun() {
        final Runnable runnable = () -> System.out.println("Thread: " + Thread.currentThread().getName());

        Executor executor = new RunExecutor();
        executor.execute(runnable);
    }

    static class RunExecutor implements Executor {
        @Override
        public void execute(final Runnable command) {
            new Thread(command).start();
            command.run();
        }
    }

    @Test
    public void threadPoolSizeTest() {
        ExecutorService executorService1 = Executors.newFixedThreadPool(1);
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        Runnable runnable1 = () -> System.out.println("Thread1: " + Thread.currentThread().getName());
        Runnable runnable2 = () -> System.out.println("Thread2: " + Thread.currentThread().getName());
        Runnable runnable3 = () -> System.out.println("Thread3: " + Thread.currentThread().getName());
        Runnable runnable4 = () -> System.out.println("Thread4: " + Thread.currentThread().getName());
        executorService1.execute(runnable1);
        executorService1.execute(runnable2);
        executorService2.execute(runnable3);
        executorService2.execute(runnable4);

        executorService1.shutdown();
        executorService2.shutdown();
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService smallService = Executors.newFixedThreadPool(1);

        Callable<String> hello = () -> {
            Thread.sleep(1000L);
            final String result = "Hello";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> mang = () -> {
            Thread.sleep(2000L);
            final String result = "Mang";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> kyu = () -> {
            Thread.sleep(3000L);
            final String result = "kyu";
            System.out.println("result = " + result);
            return result;
        };

        Instant start = Instant.now();
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, mang, kyu));
        Instant end = Instant.now();

        Instant smallStart = Instant.now();
        smallService.invokeAll(Arrays.asList(hello, mang, kyu));
        Instant smallEnd = Instant.now();
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        System.out.println("넉넉한 쓰레드 풀 의 time = " + Duration.between(start, end).getSeconds());
        System.out.println("부족한 쓰레드 풀 의 time = " + Duration.between(smallStart, smallEnd).getSeconds());
        executorService.shutdown();
        smallService.shutdown();
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Instant start = Instant.now();

        Callable<String> hello = () -> {
            Thread.sleep(1000L);
            final String result = "Hello";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> mang = () -> {
            Thread.sleep(2000L);
            final String result = "Mang";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> kyu = () -> {
            Thread.sleep(3000L);
            final String result = "kyu";
            System.out.println("result = " + result);
            return result;
        };

        String result = executorService.invokeAny(Arrays.asList(hello, mang, kyu));
        System.out.println("result = " + result + " time = " + Duration.between(start, Instant.now()).getSeconds());

        executorService.shutdown();
    }
}
