package securepay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import securepay.model.CategorizedItems;
import securepay.model.SelectedItems;
import securepay.model.gsonloader.GsonLoader;

class SelectItemsTest {
    @Test
    void test1() {
        int maxCost = 10;
        CategorizedItems categorizedItems = GsonLoader.fromJson(
                this.getClass().getResourceAsStream("/selectitemstest/test1.json"), CategorizedItems.class);
        SelectedItems selectedItems = SelectItems.selectItems(categorizedItems, maxCost);

        Assertions.assertNotNull(selectedItems);
        Assertions.assertEquals(2, selectedItems.numSelectedItems());
        Assertions.assertEquals(9, selectedItems.totalRating());
        Assertions.assertEquals(10, selectedItems.totalCost());
        Assertions.assertEquals("Category1:Item1,Category2:Item1", selectedItems.selectedItemsID());
    }

    @Test
    void test2() {
        int maxCost = 6;
        CategorizedItems categorizedItems = GsonLoader.fromJson(
                this.getClass().getResourceAsStream("/selectitemstest/test2.json"), CategorizedItems.class);
        SelectedItems selectedItems = SelectItems.selectItems(categorizedItems, maxCost);

        Assertions.assertNotNull(selectedItems);
        Assertions.assertEquals(3, selectedItems.numSelectedItems());
        Assertions.assertEquals(6, selectedItems.totalRating());
        Assertions.assertEquals(6, selectedItems.totalCost());
        Assertions.assertEquals("Category2:Item1,Category3:Item1,Category4:Item1",
                selectedItems.selectedItemsID());
    }

    @Test
    void test3() {
        int maxCost = 6;
        CategorizedItems categorizedItems = GsonLoader.fromJson(
                this.getClass().getResourceAsStream("/selectitemstest/test3.json"), CategorizedItems.class);
        SelectedItems selectedItems = SelectItems.selectItems(categorizedItems, maxCost);

        Assertions.assertNotNull(selectedItems);
        Assertions.assertEquals(1, selectedItems.numSelectedItems());
        Assertions.assertEquals(5, selectedItems.totalRating());
        Assertions.assertEquals(5, selectedItems.totalCost());
        Assertions.assertEquals("Category4:Item1", selectedItems.selectedItemsID());
    }
}