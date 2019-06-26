package securepay.generator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import securepay.model.CategorizedItems;

class CategorizedItemsGeneratorTest {

    @Test
    void generate() {
        CategorizedItems categorizedItems = CategorizedItemsGenerator.generate();
        Assertions.assertEquals(CategorizedItemsGenerator.NUM_CATEGORIES,
                categorizedItems.getCategorizedItems().size());
        Assertions.assertEquals(CategorizedItemsGenerator.NUM_ITEMS_PER_CATEGORY,
                categorizedItems.getCategorizedItems().get(1).size());
    }
}