package securepay.model.config;

public class ItemConfig {
    private int maxPrice;
    private int minPrice;

    private int maxShippingCost;
    private int minShippingCost;

    private int maxRating;
    private int minRating;

    private ItemConfig(){}

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxShippingCost() {
        return maxShippingCost;
    }

    public int getMinShippingCost() {
        return minShippingCost;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public int getMinRating() {
        return minRating;
    }
}
