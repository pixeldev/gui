package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;

import java.util.List;
import java.util.function.Predicate;

public interface MenuInventoryBuilder {

    MenuInventoryBuilder fillItem(ItemClickable item, int from, int to);

    MenuInventoryBuilder fillRow(ItemClickable item, int row);

    MenuInventoryBuilder fillColumn(ItemClickable item, int column);

    MenuInventoryBuilder fillBorders(ItemClickable item);

    MenuInventoryBuilder setItems(List<ItemClickable> items);

    MenuInventoryBuilder addItem(ItemClickable item, int... slots);

    MenuInventoryBuilder addItem(ItemClickable item);

    MenuInventoryBuilder setOpenAction(Predicate<Inventory> action);

    MenuInventoryBuilder setCloseAction(Predicate<Inventory> action);

    MenuInventoryBuilder setIntroduceItems(boolean canIntroduceItems);

    Inventory build();
}
