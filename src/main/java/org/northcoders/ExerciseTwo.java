package org.northcoders;

import java.util.concurrent.CompletableFuture;

public class ExerciseTwo {

    public static void sayHelloWorldOnDelay() {

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.print("Hello...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture.runAsync(() ->
        {
            try {
                Thread.sleep(5000);
                System.out.print("...world!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
