package dk.easv;

public class RunnableCounter implements Runnable {

    // The field containing the actual value of the counter.
    private Integer counter;

    // Constructor which initializes the counter
    public RunnableCounter(int start)
    {
        counter = start;
    }

    @Override
    public void run() {
        try {
            // This never-ending loop means that the task will never stop working by it's own.
            while(true) {
                System.out.println("Thread: " + Thread.currentThread().getId() + "\tCount: " + counter++);

                // Will make the current thread wait 1/10th of a second (100 ms)
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            // This catch-block finishes the work of the thread when it has been interrupted from the outside.
            System.out.println("Thread " + Thread.currentThread().getId() + " was interrupted!");
        }
    }
}
