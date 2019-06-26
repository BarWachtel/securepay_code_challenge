package securepay.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategorizedItemsTest {

    @Test
    void generate() {
        CategorizedItems categorizedItems = CategorizedItems.generate();
        Assertions.assertEquals(CategorizedItems.NUM_CATEGORIES,
                categorizedItems.getCategorizedItems().size());
        Assertions.assertEquals(CategorizedItems.NUM_ITEMS_PER_CATEGORY,
                categorizedItems.getCategorizedItems().get(1).size());
    }
}