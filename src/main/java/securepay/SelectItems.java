package securepay;

import securepay.model.CategorizedItems;
import securepay.model.Item;
import securepay.model.SelectedItems;
import securepay.model.config.CategorizedItemsConfig;
import securepay.model.gsonloader.GsonLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class SelectItems {
    public static void main(String[] args) {
        InputStream inputStream = null;
        if (args.length > 0) {
            try {
                inputStream = new FileInputStream(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("Input parameter should be a full path to Categorized Items config json");
                System.err.println("Received: " + args[0]);
                System.exit(1);
            }
        } else {
            inputStream = SelectItems.class.getResourceAsStream("/default_cat_item_config.json");
        }

        CategorizedItemsConfig categorizedItemsConfig = GsonLoader.fromJson(
                inputStream,
                CategorizedItemsConfig.class
        );
        CategorizedItems categorizedItems = CategorizedItems.generate(categorizedItemsConfig);

        SelectedItems selectedItems = selectItems(categorizedItems, categorizedItemsConfig.getMaxCost());

        System.out.println("Item coordinates:");
        System.out.println(selectedItems.selectedItemsID());
        System.out.println();

        System.out.println("Total rating: " + selectedItems.totalRating());
        System.out.println();

        System.out.println("Total cost: " + selectedItems.totalCost());
        System.out.println();
    }

    public static SelectedItems selectItems(CategorizedItems categorizedItems, int maxCost) {
        DpCell[][] dp = new DpCell[categorizedItems.numCategories() + 1][maxCost + 1];

        for (int categoryNumber = 0; categoryNumber <= categorizedItems.numCategories(); categoryNumber++) {
            for (int currMaxCost = 0; currMaxCost <= maxCost; currMaxCost++) {
                if (categoryNumber == 0 || currMaxCost == 0) {
                    dp[categoryNumber][currMaxCost] = new DpCell(0);
                } else {
                    // Best practice for passing primitives to streams
                    final int finalCost = currMaxCost;
                    final int finalCat = categoryNumber;

                    final Optional<Item> optionalMax = categorizedItems.getCategory(categoryNumber)
                            .stream()
                            .filter(item -> item.getTotalPrice() <= finalCost)
                            .max(Comparator.comparingInt(x ->
                                    x.getRating() + dp[finalCat - 1][finalCost - x.getTotalPrice()].rating));

                    DpCell maxDpCell = dp[categoryNumber - 1][currMaxCost];
                    if (optionalMax.isPresent()) {
                        Item max = optionalMax.get();
                        DpCell newDpCell = new DpCell(max, dp[categoryNumber - 1][currMaxCost - max.getTotalPrice()]);

                        if (newDpCell.rating > maxDpCell.rating) {
                            maxDpCell = newDpCell;
                        }
                    }

                    dp[categoryNumber][currMaxCost] = maxDpCell;
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
