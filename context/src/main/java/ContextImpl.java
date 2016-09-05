
public class ContextImpl implements Context {

    private final ThreadPoolImpl threadPool;

    public ContextImpl(ThreadPoolImpl threadPool) {
        this.threadPool = threadPool;
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
