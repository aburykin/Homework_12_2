public class ExecutionManagerImpl implements ExecutionManager {

    private final ThreadPoolImpl threadPool = new ThreadPoolImpl();
    private Thread mainThread;

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        for (Runnable task : tasks) threadPool.addTask(task);

        Thread mainThread = new Thread(() -> {
            this.threadPool.start();
            while (!this.threadPool.isFinished()) {
            }
            new Thread(callback).start();
        });
        this.mainThread = mainThread;
        mainThread.start();

        return new ContextImpl(threadPool);
    }


    public ThreadPoolImpl getThreadPool() {
        return threadPool;
    }

    public Thread getMainThread() {
        return mainThread;
    }
}