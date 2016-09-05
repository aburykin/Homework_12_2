import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ABurykin on 02.09.2016.
 */
public class ThreadPoolImpl extends Thread {

    private final List<Runnable> tasks = new ArrayList();
    private final List<Thread> runningTasks = new ArrayList();
    private AtomicInteger countCompletedTasks = new AtomicInteger(0);
    private AtomicInteger countFailedTasks = new AtomicInteger(0);
    private AtomicInteger countInterraptTasks = new AtomicInteger(0);

    @Override
    public void start() {
        for (Runnable task : tasks) {
            ((Task) task).setThreadPool(this);
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

    // методы для работы со счетчиками

    public void addCountFailedTasks() {
        countFailedTasks.incrementAndGet();
    }

    public void addcountCompletedTasks() {
        countCompletedTasks.incrementAndGet();
    }

    // МЕТОДЫ ДЛЯ Context

    public int getCompletedTaskCount() {
        return countCompletedTasks.get();
    }


    public int getFailedTaskCount() {
        return countFailedTasks.get();
    }


    public int getInterruptedTaskCount() {
        return countInterraptTasks.get();
    }


    public void interrupt() {
        for (Thread task : runningTasks) {
            if (task.isAlive()) {
                task.interrupt();
                countInterraptTasks.incrementAndGet();
                return;
            }
        }
    }

    public boolean isFinished() {
        return (tasks.size() == (getCompletedTaskCount() + getFailedTaskCount() + getInterruptedTaskCount()));
    }
}
