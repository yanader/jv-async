package org.northcoders;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


public class ExerciseEleven {

    public static void sumOneThousandRandomInts() {
        int[] numbers = new int[1000];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(1, 1000);
        }

        int[] firstHalf = Arrays.copyOfRange(numbers, 0, numbers.length / 2);
        int[] secondHalf = Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<Integer>> list = new ArrayList();

        addCallableToList(firstHalf, list, executorService);
        addCallableToList(secondHalf, list, executorService);


        CompletableFuture<Integer> firstTotal = CompletableFuture.supplyAsync(() -> {
            try {
                return list.getFirst().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Integer> secondTotal = CompletableFuture.supplyAsync(() -> {
            try {
                return list.get(1).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        int result;
        try {
            result = firstTotal.thenCombine(secondTotal, Integer::sum).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        executorService.close();


    }

    private static void addCallableToList(int[] arr, List<Future<Integer>> list, ExecutorService executorService) {
        Callable<Integer> callOne = () -> {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            return sum;
        };
        list.add(executorService.submit(callOne));
    }
}
