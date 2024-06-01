package org.northcoders;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class ExerciseOne {

    public static void sayHelloWithDelay()  {

        CompletableFuture.runAsync(() -> {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello ONE");
        });

        System.out.println("hello TWO");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
