package org.northcoders;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ExerciseFour {

    public static void returnHelloThrowError() {

        Random r = new Random();
        int rOne = r.nextInt(10);
        int rTwo = r.nextInt(10);

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rOne * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello... ";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rTwo * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "...world ";
        });

        try {
            String greeting = hello.thenCombine(world, (s1, s2) -> s1 + s2).orTimeout(5, TimeUnit.SECONDS).get();
            System.out.println(greeting);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Timeout buddy");
//            throw new RuntimeException(e);
        }

    }
}
