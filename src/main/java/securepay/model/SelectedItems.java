package securepay.model;

import java.util.List;

public class SelectedItems {
    private final List<Item> selectedItems;

    public SelectedItems(List<Item> selectedItems) {
        selectedItems.sort((Item x, Item y) -> {
            if (x.getCategoryNumber() != y.getCategoryNumber()) {
                return x.getCategoryNumber() - y.getCategoryNumber();
            } else {
                return x.getItemNumber() - y.getItemNumber();
            }
        });
        this.selectedItems = selectedItems;
    }

    public int numSelectedItems() {
        return this.selectedItems.size();
    }

    public int totalRating() {
        return this.selectedItems.stream().reduce(0,
                (partialResult, item) -> partialResult + item.getRating(), Integer::sum);
    }

    public int totalCost() {
        return this.selectedItems.stream().reduce(0,
                (partialResult, item) -> partialResult + item.getTotalPrice(), Integer::sum);
    }

    public String selectedItemsID() {
        return selectedItems.stream()
                .map(x -> "Category" + x.getCategoryNumber() + ":" + "Item" + x.getItemNumber())
                .reduce("", (x, y) -> x + "," + y).substring(1);
    }
}
