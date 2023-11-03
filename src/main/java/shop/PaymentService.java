package shop;

public class PaymentService {
    public Integer pay(User user, Integer sum) {
        return (int) (Math.random() * 1000) + 1;
    }
}
