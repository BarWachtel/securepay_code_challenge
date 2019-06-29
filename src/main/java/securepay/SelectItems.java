package securepay;

import securepay.model.CategorizedItems;
import securepay.model.Item;
import securepay.model.SelectedItems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SelectItems {
    public static SelectedItems selectItems(CategorizedItems categorizedItems, int maxCost) {
        DpCell[][] dp = new DpCell[categorizedItems.numCategories() + 1][maxCost + 1];

        for (int categoryNumber = 0; categoryNumber <= categorizedItems.numCategories(); categoryNumber++) {
            for (int currMaxCost = 0; currMaxCost <= maxCost; currMaxCost++) {
                if (categoryNumber == 0 || currMaxCost == 0) {
                    dp[categoryNumber][currMaxCost] = new DpCell(0);
                } else {
                    int finalCost = currMaxCost;
                    int finalCat = categoryNumber;
                    List<Item> collect = categorizedItems.getCategory(categoryNumber)
                            .stream().filter(item -> item.getTotalPrice() <= finalCost)
                            .collect(Collectors.toList());

                    DpCell newDpCell = dp[categoryNumber - 1][currMaxCost];
                    if (collect.size() > 0) {
                        Item max = collect.stream()
                                .max(Comparator.comparingInt(x ->
                                        x.getRating() + dp[finalCat - 1][finalCost - x.getTotalPrice()].rating))
                                .get();
                        if (max.getRating() + dp[categoryNumber - 1][currMaxCost - max.getTotalPrice()].rating > dp[categoryNumber - 1][currMaxCost].rating) {
                            newDpCell = new DpCell(max, dp[categoryNumber - 1][currMaxCost - max.getTotalPrice()]);
                        }
                    }

                    dp[categoryNumber][currMaxCost] = newDpCell;
                }
            }

        }

        return new SelectedItems(dp[categorizedItems.numCategories()][maxCost].getItemsList());
    }

    private static class DpCell {
        private Item item;
        private DpCell previousCell;
        private int rating;

        DpCell(int rating) {
            this.rating = rating;
            this.item = null;
            this.previousCell = null;
        }

        DpCell(Item item, DpCell previousCell) {
            this.rating = item.getRating() + previousCell.rating;
            this.item = item;
            this.previousCell = previousCell;
        }

        List<Item> getItemsList() {
            if (this.item == null || this.previousCell == null) {
                return new ArrayList<>();
            } else {
                List<Item> itemsList = previousCell.getItemsList();
                itemsList.add(this.item);
                return itemsList;
            }
        }
    }
}
