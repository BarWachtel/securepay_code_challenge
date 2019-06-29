package securepay.model;

import securepay.model.config.CategorizedItemsConfig;
import securepay.model.config.ItemConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorizedItems {
    private Map<Integer, List<Item>> categorizedItems;

    private CategorizedItems() {
        this.categorizedItems = new HashMap<>();
    }

    public Map<Integer, List<Item>> getCategorizedItemsMap() {
        return categorizedItems;
    }

    public static CategorizedItems generate(CategorizedItemsConfig categorizedItemsConfig) {
        CategorizedItems categorizedItems = new CategorizedItems();

        ItemConfig itemConfig = categorizedItemsConfig.getItemConfig();
        for (int categoryNum = 1; categoryNum <= categorizedItemsConfig.getNumCategories(); categoryNum++) {
            List<Item> items = new ArrayList<>();
            for (int itemNumber = 1; itemNumber <= categorizedItemsConfig.getNumItems(); itemNumber++) {

                items.add(Item.generate(categoryNum, itemNumber, itemConfig));
            }
            categorizedItems.addCategory(categoryNum, items);
        }
        return categorizedItems;
    }

    private void addCategory(int categoryNum, List<Item> items) {
        this.categorizedItems.put(categoryNum, items);
    }

    public int numCategories() {
        return this.categorizedItems.size();
    }

    public List<Item> getCategory(int categoryNumber) {
        return this.categorizedItems.get(categoryNumber);
    }
}
