import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        Executors.newVirtualThreadPerTaskExecutor();
    }
}
