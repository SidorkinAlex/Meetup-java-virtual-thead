package shop;

import java.util.Arrays;
import java.util.List;

public class Cart {
    private List<ShopItem> items;

    public Cart() {
        this.items = Arrays.asList(
                new ShopItem(100),
                new ShopItem(300),
                new ShopItem(400),
                new ShopItem(500)
        );
    }

    public List<ShopItem> items() {
        return items;
    }

}
