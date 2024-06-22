package org.northcoders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExerciseTwelve {

    public static void aSyncProcessWebPage() {

        String[] lines;
        try {
             lines = fetchWebPage("https://en.wikipedia.org/wiki/Elvis_Presley").split("\n");
            System.out.println("The elvis wiki has " + lines.length + " lines");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Future<Integer>> list = new ArrayList<>();

        for (String line: lines) {
            Callable<Integer> call = () -> {
                int count = 0;
                String[] words = line.split(" ");
                for (String word : words) {
                    if (wordIsCapitalised(word)) count++;
                }
                return count;
            };
            list.add(executor.submit(call));
        }

        int capitalisedWords = 0;
        for (Future<Integer> future : list) {
            try {
                capitalisedWords += future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.close();
        System.out.println("On the elvis wiki, there are " + capitalisedWords + " capitalised words!");

    }

    private static String fetchWebPage(String url) throws IOException {
        URL webpage = URI.create(url).toURL();
        BufferedReader reader = new BufferedReader(new InputStreamReader(webpage.openStream()));
        return reader.lines().collect(Collectors.joining("\n"));
    }

    private static boolean wordIsCapitalised(String word) {
        if (word.isEmpty() || word.isBlank()) return false;
        if (Character.isLowerCase(word.charAt(0))) return false;
        for (int i = 1; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) return false;
            if (!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
}
