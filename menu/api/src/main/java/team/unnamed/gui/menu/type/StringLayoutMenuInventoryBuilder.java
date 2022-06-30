package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.layoutLines = new ArrayList<>();
        this.layoutItems = new HashMap<>();
    }

    public StringLayoutMenuInventoryBuilder setLayoutItem(char identifier, ItemClickable item) {
        this.layoutItems.put(identifier, isNotNull(item));
        return back();
    }

    public StringLayoutMenuInventoryBuilder setLayoutLines(String... lines) {
        for (String line : lines) {
            isState(line.length() == 9,
                    "Cannot add layout line '" + line + "' because length is minor than 9");
            this.layoutLines.add(line.trim());
        }
        return back();
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

                addItem(itemClickable.clone(slotIndex));
            }
        }

        return super.build();
    }

    @Override
    protected StringLayoutMenuInventoryBuilder back() {
        return this;
    }

}
