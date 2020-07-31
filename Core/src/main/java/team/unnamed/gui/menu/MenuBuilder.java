package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;
import team.unnamed.gui.item.ItemClickable;

import java.util.Map;
import java.util.Optional;

public interface MenuBuilder {

    MenuBuilder setRows(int rows);

    MenuBuilder fillItem(ItemClickable itemClickable, int from, int to);

    MenuBuilder setItems(Map<Integer, ItemClickable> items);

    MenuBuilder addItem(int slot, ItemClickable itemClickable);

    MenuBuilder openEvent(OpenMenuAction openMenuAction);

    MenuBuilder closeEvent(CloseMenuAction closeMenuAction);

    MenuBuilder cancelClick(boolean cancelClick);

    String getTitle();

    int getRows();

    Map<Integer, ItemClickable> getItems();

    Optional<OpenMenuAction> getOpenMenuAction();

    Optional<CloseMenuAction> getCloseMenuAction();

    Optional<ItemClickable> getItemToFill();

    int getFrom();

    int getTo();

    boolean isCancelClick();

    default Optional<ItemClickable> getItemClickable(int slot) {
        return Optional.ofNullable(getItems().get(slot));
    }

    default Inventory build() {
        return new DefaultMenuHolder(this).getInventory();
    }

    static MenuBuilder newBuilder(String title) {
        return new DefaultMenuBuilder(title);
    }

    static MenuBuilder newBuilder(String title, int rows) {
        return new DefaultMenuBuilder(title, rows);
    }

}