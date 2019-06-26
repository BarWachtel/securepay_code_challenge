package securepay.model;

import securepay.generator.NumberGenerator;

public class Item {
    private static final int MAX_PRICE = 20;
    private static final int MIN_PRICE = 1;

    private static final int MAX_SHIPPING_COST = 5;
    private static final int MIN_SHIPPING_COST = 2;

    private static final int MAX_RATING = 5;
    private static final int MIN_RATING = 1;

    private final int itemNumber;
    private final int categoryNumber;
    private final int price;
    private final int shippingCost;
    private final int rating;

    private Item(int itemNumber, int categoryNumber, int price, int shippingCost, int rating) {
        this.itemNumber = itemNumber;
        this.categoryNumber = categoryNumber;
        this.price = price;
        this.shippingCost = shippingCost;
        this.rating = rating;
    }

    public static Item generate(int itemNumber, int categoryNumber) {
        return new Item(itemNumber, categoryNumber,
                NumberGenerator.inRange(MAX_PRICE, MIN_PRICE),
                NumberGenerator.inRange(MAX_SHIPPING_COST, MIN_SHIPPING_COST),
                NumberGenerator.inRange(MAX_RATING, MIN_RATING));
    }
}
