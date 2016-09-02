
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ABurykin on 02.09.2016.
 */
public class ThreadPoolImpl extends Thread {

    private final List<Runnable> tasks = new ArrayList();
    private final List<Thread> runningTasks = new ArrayList();
    private volatile static int countCompletedTasks = 0;
    private volatile static int countFailedTasks = 0;
    private volatile static int countInterraptTasks = 0;

    @Override
    public void start() {
        for (Runnable task : tasks) {
            Thread x = new Thread(task);
            runningTasks.add(x);
            x.start();
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addTask(Runnable task) {
        tasks.add(task);
    }

    // методы ля работы со счетчиками

    public static void addCountFailedTasks() {
        countFailedTasks++;
    }

    public static void addcountCompletedTasks() {
        countCompletedTasks++;
    }

    // МЕТОДЫ ДЛЯ Context

    public int getCompletedTaskCount() {
        return countCompletedTasks;
    }


    public int getFailedTaskCount() {
        return countFailedTasks;
    }


    public int getInterruptedTaskCount() {
        return countInterraptTasks;
    }


    public void interrupt() {
        for (Thread task : runningTasks) {
            if (task.isAlive()) {
                task.interrupt();
                countInterraptTasks++;
                return;
            }
        }
    }

    public boolean isFinished() {
        return (tasks.size() == (countCompletedTasks + countFailedTasks + countInterraptTasks));
    }
}
