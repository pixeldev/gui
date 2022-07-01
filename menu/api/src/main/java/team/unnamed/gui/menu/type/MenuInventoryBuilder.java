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

    MenuInventoryBuilder items(List<ItemClickable> items);

    MenuInventoryBuilder item(ItemClickable item, int... slots);

    MenuInventoryBuilder item(ItemClickable item);

    MenuInventoryBuilder openAction(Predicate<Inventory> action);

    MenuInventoryBuilder closeAction(Predicate<Inventory> action);

    MenuInventoryBuilder introduceItems(boolean canIntroduceItems);

    Inventory build();
}
