import shop.*;

public class LineLogicTest {

    public static void main(String[] args) {
        Repo repo = new Repo();

        CartServise cartServise = new CartServise();
        UserServise userServise = new UserServise();
        EmailService emailService = new EmailService();
        PaymentService paymentService = new PaymentService();

        long startTime = System.currentTimeMillis();


        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    System.out.println("thread start" + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
                    User user = userServise.findUserByName("Fuller Gonzalez");
                    if(!repo.contans(user)){
                        repo.save(user);
                    }
                    Cart cart = cartServise.loadCartFor(user);
                    Integer total = cart.items().stream()
                            .mapToInt(item -> item.getCost() )
                            .sum();
                    Integer transactionId = paymentService.pay(user,total);
                    emailService.send(user,cart,transactionId);
                    System.out.println(total.toString() +" transactionId:"+ transactionId);
                    System.out.println("thread ended" + Thread.currentThread().threadId() + " " + Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time of created Thread " + (endTime - startTime));

    }
}
