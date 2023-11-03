import shop.*;

public class EmptyThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            new Thread(() -> {
            }).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time of created Thread " + (endTime - startTime));

    }
}
