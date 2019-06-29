package securepay.model.config;

import java.io.InputStream;

public class CategorizedItemsConfig {
    private int numCategories;
    private int numItems;
    private ItemConfig itemConfig;

    private CategorizedItemsConfig() {}

    public int getNumCategories() {
        return numCategories;
    }

    public int getNumItems() {
        return numItems;
    }

    public ItemConfig getItemConfig() {
        return itemConfig;
    }

    public static CategorizedItemsConfig fromJsonFile(InputStream fileStream) {
        return null;
    }
}
