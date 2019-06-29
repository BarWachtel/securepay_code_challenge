package securepay.model;

import securepay.generator.NumberGenerator;
import securepay.model.config.ItemConfig;

public class Item {
    private final int itemNumber;
    private final int categoryNumber;
    private final int price;
    private final int shippingCost;
    private final int rating;

    public Item(int itemNumber, int categoryNumber, int price, int shippingCost, int rating) {
        this.itemNumber = itemNumber;
        this.categoryNumber = categoryNumber;
        this.price = price;
        this.shippingCost = shippingCost;
        this.rating = rating;
    }

    public static Item generate(int categoryNumber, int itemNumber, ItemConfig itemConfig) {
        return new Item(itemNumber, categoryNumber,
                NumberGenerator.inRange(itemConfig.getMaxPrice(), itemConfig.getMinPrice()),
                NumberGenerator.inRange(itemConfig.getMaxShippingCost(), itemConfig.getMinShippingCost()),
                NumberGenerator.inRange(itemConfig.getMaxRating(), itemConfig.getMinRating()));
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public int getPrice() {
        return price;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public int getRating() {
        return rating;
    }

    public int getTotalPrice() {
        return getPrice() + getShippingCost();
    }
}
