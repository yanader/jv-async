package org.northcoders;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ExerciseSeven {

    public static void printHelloThreeTimes() throws InterruptedException {

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(() -> {
                for (int i = 0; i < 3; i ++) {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Hello ||| " + Thread.currentThread().getName());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Thread.sleep(6000);
    }


}
