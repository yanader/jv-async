package org.northcoders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private final ScheduledExecutorService scheduler;

    public TaskScheduler(int threadPooleSize) {
        this.scheduler = Executors.newScheduledThreadPool(threadPooleSize);
    }

    public void scheduleTask(Runnable runnable, LocalDateTime scheduledTime) {
        long delay = LocalDateTime.now().until(scheduledTime, ChronoUnit.MILLIS);

        scheduler.schedule(() -> {
            System.out.println("Start: " +LocalDateTime.now() );
            runnable.run();
            System.out.println("End: " +LocalDateTime.now() );
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
