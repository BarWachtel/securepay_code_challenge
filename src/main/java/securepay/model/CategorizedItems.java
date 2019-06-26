package securepay.model;

import java.util.List;
import java.util.Map;

public class CategorizedItems {
    private Map<Integer, List<Item>> categorizedItems;

    public Map<Integer, List<Item>> getCategorizedItems() {
        return categorizedItems;
    }
}
