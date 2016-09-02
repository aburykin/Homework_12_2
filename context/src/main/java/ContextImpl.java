
public class ContextImpl implements Context {

    private final ThreadPoolImpl threadPool;
    private final Runnable callback;

    public ContextImpl(Runnable callback, Runnable... tasks) {
        this.threadPool = new ThreadPoolImpl();
        this.callback = callback;
        for (Runnable task : tasks) {
            threadPool.addTask(task);
        }

        new Thread(() -> {
            this.threadPool.start();
            while (!this.threadPool.isFinished()) {}
            new Thread(callback).start();
        }).start();
    }

    @Override
    public int getCompletedTaskCount() {
        return threadPool.getCompletedTaskCount();
    }

    @Override
    public int getFailedTaskCount() {
        return threadPool.getFailedTaskCount();
    }

    @Override
    public int getInterruptedTaskCount() {
        return threadPool.getInterruptedTaskCount();
    }

    @Override
    public void interrupt() {
        threadPool.interrupt();
    }

    @Override
    public boolean isFinished() {
        return threadPool.isFinished();
    }

}
