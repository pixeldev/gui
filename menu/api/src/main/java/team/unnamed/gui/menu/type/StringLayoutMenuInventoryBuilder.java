package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;

import java.util.*;

import static team.unnamed.validate.Validate.isNotNull;
import static team.unnamed.validate.Validate.isState;

public class StringLayoutMenuInventoryBuilder
        extends MenuInventoryBuilderLayout<StringLayoutMenuInventoryBuilder> {

    protected final Map<Character, ItemClickable> layoutItems;
    protected final List<String> layoutLines;

    protected StringLayoutMenuInventoryBuilder(String title) {
        this(title, 6);
    }

    protected StringLayoutMenuInventoryBuilder(String title, int rows) {
        super(title, rows);
        this.layoutLines = new ArrayList<>(rows);
        this.layoutItems = new HashMap<>();
    }

    public StringLayoutMenuInventoryBuilder layoutItem(char identifier, ItemClickable item) {
        this.layoutItems.put(identifier, isNotNull(item));
        return back();
    }

    public StringLayoutMenuInventoryBuilder layoutLines(Iterable<String> lines) {
        for (String line : lines) {
            line = line.trim();
            isState(line.length() == 9,
                    "Cannot add layout line '" + line + "' because length is minor than 9");
            this.layoutLines.add(line.trim());
        }

        return back();
    }

    public StringLayoutMenuInventoryBuilder layoutLines(String... lines) {
        return layoutLines(Arrays.asList(lines));
    }

    @Override
    public Inventory build() {
        int slotIndex = 0;

        for (String layoutLine : this.layoutLines) {
            for (char c : layoutLine.toCharArray()) {
                ItemClickable itemClickable = this.layoutItems.get(c);

                if (itemClickable == null) {
                    slotIndex++;
                    continue;
                }

                item(itemClickable.clone(slotIndex));
            }
        }

        return super.build();
    }

    @Override
    protected StringLayoutMenuInventoryBuilder back() {
        return this;
    }

}
