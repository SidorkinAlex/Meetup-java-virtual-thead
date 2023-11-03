import shop.*;

import java.sql.Timestamp;

public class EmptyVirtualThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Thread.ofVirtual().name("Virtual Thread " + i).start(
                    () -> {
                    }
            );
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time of created Virtual Thread " + (endTime - startTime));
    }
}
