package org.northcoders;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

public class ExerciseThree {

    public static void returnAndCombineHelloWorld()  {

        CompletableFuture<String> partOne =  CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello...";
        });

        CompletableFuture<String> partTwo = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return " ...world";
        });

        BiFunction<String, String, String> biFunc = (stringOne, stringTwo) -> stringOne + stringTwo;


        try {
            System.out.println(partOne.thenCombine(partTwo, biFunc).get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
