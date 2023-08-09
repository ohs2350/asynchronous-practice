package com.example.asynchronous_practice.java7;

import java.util.concurrent.RecursiveTask;

public class SimpleSumTask extends RecursiveTask<Long> {
    long from;
    long to;

    SimpleSumTask(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public Long compute() {
        long size = to - from;

        if(size <= 5)     // 더할 숫자가 5개 이하면
            return sum(); // 숫자의 합을 반환

        long half = (from+to)/2;

        // 범위를 반으로 나눠서 두 개의 작업을 생성
        SimpleSumTask leftSum  = new SimpleSumTask(from, half);
        SimpleSumTask rightSum = new SimpleSumTask(half+1, to);

        leftSum.fork();

        return rightSum.compute() + leftSum.join();
    }

    long sum() { // from~to의 모든 숫자를 더한 결과를 반환
        long tmp = 0L;

        for(long i=from;i<=to;i++)
            tmp += i;

        return tmp;
    }
}
