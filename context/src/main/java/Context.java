
public interface Context {

    int getCompletedTaskCount(); //возвращает количество тасков, которые на текущий момент успешно выполнились.

    int getFailedTaskCount(); //возвращает количество тасков, при выполнении которых произошел Exception.

    int getInterruptedTaskCount(); //отменяет выполнения тасков, которые еще не начали выполняться.

    void interrupt(); // возвращает количество тасков, которые не были выполены из-за отмены (вызовом предыдущего метода).

    boolean isFinished(); //  вернет true, если все таски были выполнены или отменены, false в противном случае.

}
