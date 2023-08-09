package com.example.asynchronous_practice.java7;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinMain {
    static final ForkJoinPool pool = new ForkJoinPool();

    public static void main(String[] args) {
        long from = 1L;
        long to   = 100_000_000L;

        SimpleSumTask task = new SimpleSumTask(from, to);

        long start = System.currentTimeMillis(); // 시작시간 초기화
        Long result = pool.invoke(task);

        System.out.println("Elapsed time(Multi Thread): "+(System.currentTimeMillis()-start));
        System.out.printf("sum of %d~%d = %d%n", from, to, result);
        System.out.println();

        result = 0L;
        start = System.currentTimeMillis(); // 시작시간 초기화
        for(long i=from;i<=to;i++)
            result += i;

        System.out.println("Elapsed time(Single Thread): "+(System.currentTimeMillis()-start));
        System.out.printf("sum of %d~%d = %d%n", from, to, result);
    }
}
