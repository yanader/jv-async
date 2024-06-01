package org.northcoders;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {

//        ExerciseOne.sayHelloWithDelay();
//        ExerciseTwo.sayHelloWorldOnDelay();
//        ExerciseThree.returnAndCombineHelloWorld();
//        for (int i = 0; i < 10; i++) {
//            ExerciseFour.returnHelloThrowError();
//        }
//        ExerciseFive.printBigFactorials();
//        ExerciseSix.printBigFactorialsAndInterruptWithPoem();
//        try {
//            ExerciseSeven.printHelloThreeTimes();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        ExerciseEight.multiThreadFactorialPrinting();
        exerciseNineTaskScheduler();



    }

    public static void exerciseNineTaskScheduler() {
        TaskScheduler taskScheduler = new TaskScheduler(2);
        Runnable taskOne = () -> System.out.println("taskOne");
        Runnable taskTwo = () -> System.out.println("taskTwo");
        LocalDateTime taskOneTime = LocalDateTime.now().plusSeconds(5);
        LocalDateTime taskTwoTime = LocalDateTime.now().plusSeconds(3);

        taskScheduler.scheduleTask(taskOne, taskOneTime);
        taskScheduler.scheduleTask(taskTwo, taskTwoTime);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        taskScheduler.shutdown();

    }
}