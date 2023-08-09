package com.example.asynchronous_practice.java5;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.concurrent.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CallableAndFutureTest {
    @Test
    public void simpleCallable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                return "test";
            }
        };

        Future<String> result = executorService.submit(callable);
        Assert.hasText("test", result.get());
        executorService.shutdown();
    }

    @Test
    void isDone_False() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        Future<String> future = executorService.submit(callable);

        assertThat(future.isDone()).isFalse();
        executorService.shutdown();
    }

    @Test
    void isDone_True() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        Future<String> future = executorService.submit(callable);

        while (future.isDone()) {
            assertThat(future.isDone()).isTrue();
            executorService.shutdown();
        }
    }

    private Callable<String> callable() {
        return new Callable<String>() {
            @Override
            public String call() throws InterruptedException {
                Thread.sleep(3000L);
                return "Thread: " + Thread.currentThread().getName();
            }
        };
    }
}
