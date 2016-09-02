/**
 * Created by ABurykin on 02.09.2016.
 */
public class Task implements Runnable{

    private final long timeWorking;
    private final String taskName;

    public Task(long timeWorking, String taskName) {
        this.timeWorking = timeWorking;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started");
        try {
            Thread.currentThread().sleep(timeWorking);
            ThreadPoolImpl.addcountCompletedTasks();
        } catch (InterruptedException e) {
            ThreadPoolImpl.addCountFailedTasks();

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


}
