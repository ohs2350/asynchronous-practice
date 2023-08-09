package com.example.asynchronous_practice.java8;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureRunTest {
    @Test
    void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        future.get();
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Thread: " + Thread.currentThread().getName();
        });
        System.out.println(future.get());
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @Test
    void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Thread: " + Thread.currentThread().getName();
        }).thenApply(String::toUpperCase);

        System.out.println(future.get());
    }

    @Test
    void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return "Thread: " + Thread.currentThread().getName();
        }).thenAccept(s -> {
            System.out.println(s.toUpperCase());
        });

        future.get();
    }


    @Test
    void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return "Thread: " + Thread.currentThread().getName();
        }).thenRun(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });

        future.get();
    }

}
