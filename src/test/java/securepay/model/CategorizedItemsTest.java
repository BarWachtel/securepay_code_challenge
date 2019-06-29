package securepay.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import securepay.model.config.CategorizedItemsConfig;
import securepay.model.gsonloader.GsonLoader;

import java.util.List;
import java.util.Map;

class CategorizedItemsTest {

    @Test
    void generate() {
        CategorizedItemsConfig catItemsConfig = GsonLoader.fromJson(
                this.getClass().getResourceAsStream("/categorizeditemtest/cat_item_config.json"),
                CategorizedItemsConfig.class);
        CategorizedItems categorizedItems = CategorizedItems.generate(catItemsConfig);
        Assertions.assertEquals(catItemsConfig.getNumCategories(),
                categorizedItems.getCategorizedItemsMap().size());
        Assertions.assertEquals(catItemsConfig.getNumItems(),
                categorizedItems.getCategorizedItemsMap().get(1).size());
    }

    @Test
    void fromJson() {
        CategorizedItems categorizedItems = GsonLoader.fromJson(
                this.getClass().getResourceAsStream("/categorizeditemtest/fromjson.json"),
                CategorizedItems.class);
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