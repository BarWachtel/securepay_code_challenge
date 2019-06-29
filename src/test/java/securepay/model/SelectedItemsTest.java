package securepay.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SelectedItemsTest {
    private static List<Item> selectedItems;

    @BeforeAll
    static void loadItemList() {
        selectedItems = Arrays.asList(
                new Item(1, 3, 3, 2, 5),
                new Item(2, 2, 1, 2, 4));
    }

    @Test
    void numSelectedItems() {
        Assertions.assertEquals(2, new SelectedItems(selectedItems).numSelectedItems());
    }

    @Test
    void totalRating() {
        Assertions.assertEquals(9, new SelectedItems(selectedItems).totalRating());
    }

    @Test
    void totalCost() {
        Assertions.assertEquals(8, new SelectedItems(selectedItems).totalCost());
    }

    @Test
    void selectedItemsID() {
        Assertions.assertEquals("Category2:Item2,Category3:Item1",
                new SelectedItems(selectedItems).selectedItemsID());
    }

}