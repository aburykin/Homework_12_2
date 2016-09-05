
/**
 * Created by ABurykin on 02.09.2016.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Task[] tasks = new Task[20];
        for (int i = 0; i < 20; i++)
            tasks[i] = new Task(3000L, "Задача " + i);

        ExecutionManagerImpl manager = new ExecutionManagerImpl();
        Context context = manager.execute(new CallbackImpl(), tasks);

        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            context.interrupt(); // останавливает случайный поток
            System.out.println("\nТекущая информация " + i);
            System.out.println("CompletedTask = " + context.getCompletedTaskCount());
            System.out.println("FailedTask = " + context.getFailedTaskCount());
            System.out.println("InterruptedTask = " + context.getInterruptedTaskCount());
            System.out.println();
        }

        manager.getMainThread().join();
        System.out.println("\n------------------------------\n\nФинальная информация ");
        System.out.println("CompletedTask = " + context.getCompletedTaskCount());
        System.out.println("FailedTask = " + context.getFailedTaskCount());
        System.out.println("InterruptedTask = " + context.getInterruptedTaskCount());
        System.out.println();
    }
}