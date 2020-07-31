package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;

import java.util.List;

public interface MenuBuilder {

    MenuBuilder setRows(int rows);

    MenuBuilder fillItem(ItemClickable itemClickable, int from, int to);

    MenuBuilder setItems(List<ItemClickable> items);

    MenuBuilder addItem(ItemClickable itemClickable);

    MenuBuilder openEvent(OpenMenuAction openMenuAction);

    MenuBuilder closeEvent(CloseMenuAction closeMenuAction);

    MenuBuilder cancelClick(boolean cancelClick);

    Inventory build();

    static MenuBuilder newBuilder(String title) {
        return new DefaultMenuBuilder(title);
    }

    static MenuBuilder newBuilder(String title, int rows) {
        return new DefaultMenuBuilder(title, rows);
    }

}