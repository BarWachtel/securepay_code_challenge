package securepay.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class CategorizedItemsTest {

    @Test
    void generate() {
        CategorizedItems categorizedItems = CategorizedItems.generate();
        Assertions.assertEquals(CategorizedItems.NUM_CATEGORIES,
                categorizedItems.getCategorizedItemsMap().size());
        Assertions.assertEquals(CategorizedItems.NUM_ITEMS_PER_CATEGORY,
                categorizedItems.getCategorizedItemsMap().get(1).size());
    }

    @Test
    void fromJson() {
        CategorizedItems categorizedItems = CategorizedItems.fromJson(
                this.getClass().getResourceAsStream("fromjson_categorized_items_test.json"));
        Map<Integer, List<Item>> categorizedItemsMap = categorizedItems.getCategorizedItemsMap();
        Assertions.assertEquals(2, categorizedItemsMap.size());
        List<Item> items = categorizedItemsMap.get(1);
        Assertions.assertEquals(2, items.size());
        Item item = items.get(0);
        Assertions.assertEquals(1, item.getCategoryNumber());
        Assertions.assertEquals(1, item.getItemNumber());
        Assertions.assertEquals(20, item.getPrice());
        Assertions.assertEquals(3, item.getShippingCost());
        Assertions.assertEquals(2, item.getRating());
    }
}