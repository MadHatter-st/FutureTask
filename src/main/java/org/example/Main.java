package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Math.PI;
import static java.lang.String.format;

public class Main {



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        double a =0;
        double b =PI;
        double h = 10E-6;

        ExecutorService threadPool = Executors.newFixedThreadPool(12);
        Counter counter = new Counter();

        long start = System.currentTimeMillis();

        List<Future<Double>> futures = new ArrayList<>();


        for (double i = 0; i < 3.14; i+=h) {
            final double j = i;
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> counter.count(j,j+h,h),
                            threadPool
                    ));
        }

        double value = 0;
        for (Future<Double> future : futures) {
            value += future.get();
        }

        System.out.println("Executed by "+(System.currentTimeMillis() - start)+" value :"+value);

        threadPool.shutdown();
    }
}