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
        int threadscount = 1;

        for (int i = 0; i < 9; i++) {
            ExecutorService threadPool = Executors.newFixedThreadPool(threadscount);
            Counter counter = new Counter();

            long start = System.currentTimeMillis();

            List<Future<Double>> futures = new ArrayList<>();


            for (double k = 0; k < 3.14; k+=h) {
                final double j = k;
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

            System.out.println("Executed by "+(System.currentTimeMillis() - start)+" value :"+value+
                    " threadscount ="+threadscount);

            threadPool.shutdown();
            threadscount+=10;
        }
    }
}