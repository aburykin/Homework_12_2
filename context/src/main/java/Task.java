/**
 * Created by ABurykin on 02.09.2016.
 */
public class Task implements Runnable {

    private final long timeWorking;
    private final String taskName;
    private volatile ThreadPoolImpl threadPool;

    public Task(long timeWorking, String taskName) {
        this.timeWorking = timeWorking;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started");
        if (threadPool == null) throw new RuntimeException("В задании не объявлен пул, в котором она выполняется");

        try {
            Thread.currentThread().sleep(timeWorking);
            threadPool.addcountCompletedTasks();
        } catch (InterruptedException e) {
            threadPool.addCountFailedTasks();

            e.printStackTrace();
            // throw new RuntimeException("Произошла ошибка " + e.getLocalizedMessage());
        }
        System.out.println(taskName + " end");
    }

    public long getTimeWorking() {
        return timeWorking;
    }

    public String getTaskName() {
        return taskName;
    }


    public void setThreadPool(ThreadPoolImpl threadPool) {
        this.threadPool = threadPool;
    }
}
