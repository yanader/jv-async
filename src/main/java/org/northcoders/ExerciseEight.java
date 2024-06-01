package org.northcoders;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExerciseEight {


    public static void multiThreadFactorialPrinting() {
        List<Integer> numbers = List.of(5, 6, 7, 8, 9);
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            for (Integer i : numbers) {
                executor.execute(() -> {
                    System.out.println(calculateFactorial(BigInteger.valueOf(i)) + "|||" + Thread.currentThread().getName());
                });
            }
        }
    }

    private static BigInteger calculateFactorial(BigInteger num) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }
}
