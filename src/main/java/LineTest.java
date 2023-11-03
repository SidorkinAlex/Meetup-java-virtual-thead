import shop.*;

public class LineTest {
    public static void main(String[] args) {
        Repo repo = new Repo();
        CartServise cartServise = new CartServise();
        UserServise userServise = new UserServise();
        EmailService emailService = new EmailService();
        PaymentService paymentService = new PaymentService();

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
    }
}
