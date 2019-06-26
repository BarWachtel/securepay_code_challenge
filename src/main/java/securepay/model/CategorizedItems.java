package securepay.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorizedItems {
    public static final int NUM_CATEGORIES = 20;
    public static final int NUM_ITEMS_PER_CATEGORY = 10;

    private Map<Integer, List<Item>> categorizedItems;

    private CategorizedItems() {
        this.categorizedItems = new HashMap<>();
    }

    public Map<Integer, List<Item>> getCategorizedItems() {
        return categorizedItems;
    }

    public static CategorizedItems generate() {
        CategorizedItems categorizedItems = new CategorizedItems();

        for (int categoryNum = 1; categoryNum <= NUM_CATEGORIES; categoryNum++) {
            List<Item> items = new ArrayList<>();
            for (int itemNumber = 1; itemNumber <= NUM_ITEMS_PER_CATEGORY; itemNumber++) {
                items.add(Item.generate(categoryNum, itemNumber));
            }
            categorizedItems.addCategory(categoryNum, items);
        }
        return categorizedItems;
    }

    private void addCategory(int categoryNum, List<Item> items) {
        this.categorizedItems.put(categoryNum, items);
    }
}
