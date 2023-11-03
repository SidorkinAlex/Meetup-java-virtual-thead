
import shop.*;

import java.sql.Timestamp;


public class VirtualThreads {
    public static void main(String[] args) {
        Repo repo = new Repo();

        CartServise cartServise = new CartServise();
        UserServise userServise = new UserServise();
        EmailService emailService = new EmailService();
        PaymentService paymentService = new PaymentService();
        Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());

        // Print current timestamp
        System.out.println("Start Timestamp is : " + startTimestamp.getTime());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Thread.ofVirtual().name("Virtual Thread " + i).start(
                    () -> {
                        try {
                            System.out.println("thread start" + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
                            User user = userServise.findUserByName("Fuller Gonzalez");
                            if (!repo.contans(user)) {
                                repo.save(user);
                            }
                            Cart cart = cartServise.loadCartFor(user);
                            Integer total = cart.items().stream()
                                    .mapToInt(item -> item.getCost())
                                    .sum();
                            Integer transactionId = paymentService.pay(user, total);
                            emailService.send(user, cart, transactionId);
                            System.out.println("thread end" + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
            );
        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("time of created Thread " + (endTime - startTime));
        while (true){
            try {
                Thread.sleep(1000);
                long endTime = System.currentTimeMillis();
                long diff = (endTime - startTime);
                System.out.println("time diff " + diff);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
