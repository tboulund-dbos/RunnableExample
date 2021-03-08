package dk.easv;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // We create two instances of the RunnableCounter class because we want two counters working simultaneously.
        Runnable counter1 = new RunnableCounter(100);
        Runnable counter2 = new RunnableCounter(200);

        // An executor service creates a pool of threads and controls how many to run simultaneously.
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Both counters are added to the executor service - as soon as they are submitted the executor service will call the run-method of the runnables.
        executorService.submit(counter1);
        executorService.submit(counter2);

        // Tells the executor to not start new tasks and thereby shut down when all tasks have completed.
        executorService.shutdown();

        try {
            // Blocks the current thread (the main-thread) until all tasks are completed, or the timeout is reached (in this case 3 seconds)
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Will interrupt all running tasks by throwing an InterruptedException in the threads.
            executorService.shutdownNow();
        }

        // Finally the processes are completed.
        System.out.println("Process completed");
    }
}
