package org.northcoders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExerciseTen {


    public static void sumArrayParallel() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 4;

        int sum = sumArrayParallel(array, numThreads);
        System.out.println("Sum of array elements = " + sum);
    }



    public static int sumArrayParallel(int[] array, int numThreads) {

        int[][] arrays = splitArray(array, numThreads);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<Future<Integer>> list = new ArrayList<>();

        for (int[] subArray: arrays) {
            Callable<Integer> call = () -> {
                int sum = 0;
                for (int i = 0; i < subArray.length; i++) {
                    sum += subArray[i];
                }
                return sum;
            };
            list.add(executor.submit(call));
        }
        int total = 0;

        for (Future<Integer> future: list) {
            try {
                total += future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
        return total;
    }

    private static int[][] splitArray(int[] array, int numberOfArrays) {
        int[][] arrays = new int[numberOfArrays][];
        int subArraySize = array.length / numberOfArrays;
        int leftover = array.length % numberOfArrays;


        for(int i = 0; i < arrays.length; i++) {
            int startIndex = i * subArraySize;
            int endIndex = (i + 1) * subArraySize - 1;
            if (i == arrays.length - 1) endIndex += leftover;
            arrays[i] = Arrays.copyOfRange(array, startIndex, endIndex + 1);
        }
        return arrays;
    }


}
