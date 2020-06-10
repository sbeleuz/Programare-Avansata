package app;

import java.sql.Connection;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentTasks {
    private final Connection connection;

    public ConcurrentTasks(Connection connection) {
        this.connection = connection;
    }

    public void executeTasks(int noOfTasks) {
        /* https://howtodoinjava.com/java/multi-threading/java-thread-pool-executor-example/ */
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        for (int i = 1; i <= noOfTasks; i++) {
            Task task = new Task("Task " + i, connection);
            System.out.println("Created: " + task.getName());

            executor.execute(task);
        }
        executor.shutdown();

        // wait for running task to finish their execution
        /* https://www.baeldung.com/java-executor-wait-for-threads */
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
