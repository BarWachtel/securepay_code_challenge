package securepay.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import securepay.model.gsonloader.GsonLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelectedItemsTest {
    private static List<Item> selectedItems;

    @BeforeAll
    static void loadItemList() {
        selectedItems = GsonLoader.fromJson(
                SelectedItemsTest.class.getResourceAsStream("/selecteditemstest/selected_items.json"),
                new ArrayList<Item>().getClass());
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