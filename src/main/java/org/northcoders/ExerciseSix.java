package org.northcoders;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ExerciseSix {
    static String data = "85671 34262 92143 50984 24515 68356 77247 12348 56789 98760";
    static String story = "Mary had a little lamb, its fleece was white as snow.";


    public static void printBigFactorialsAndInterruptWithPoem() {
        String[] storyWords = story.split(" ");

        CompletableFuture.runAsync(() -> {
            for (BigInteger bi : getBigIntList(data)) {
                System.out.println(calculateFactorial(bi));
            }
        });

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                try {
                    Thread.sleep(100);
                    System.out.println(storyWords[finalI % storyWords.length]);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<BigInteger> getBigIntList(String data) {
        String[] parts = data.split(" ");
        return Arrays.stream(parts).map(BigInteger::new).toList();
    }

    private static BigInteger calculateFactorial(BigInteger num) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }
}
