package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.api.item.ItemClickable;
import team.unnamed.gui.api.menu.action.CloseMenuAction;
import team.unnamed.gui.api.menu.action.OpenMenuAction;

import java.util.List;

public interface MenuBuilder {

    /**
     * Sets the rows for the {@linkplain Inventory}
     * <p>
     * Remember this method will override the last value.
     *
     * @param rows The new rows for the {@linkplain Inventory} being built
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder setRows(int rows);

    /**
     * Sets the item that will be used to fill the final {@linkplain Inventory}
     *
     * @param itemClickable An object of {@linkplain ItemClickable} that will
     *                      be used to fill the {@linkplain Inventory}
     * @param from Slot where fill will start.
     * @param to Slot where fill will end.
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder fillItem(ItemClickable itemClickable, int from, int to);

    /**
     * Sets all items that will be used to build the {@linkplain Inventory}
     * <p>
     * Remember this method will override the last items value.
     *
     * @param items The new items for the {@linkplain Inventory} being built
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder setItems(List<ItemClickable> items);

    /**
     * Adds a new item to the {@linkplain Inventory}
     * <p>
     * Remember that if you add a new item with a repeated slot to a previous one
     * the previous one will be overwritten and you could have problems with that.
     *
     * @param itemClickable An object of {@linkplain ItemClickable} that will be added to build {@linkplain Inventory}
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder addItem(ItemClickable itemClickable);

    /**
     * Adds an action that will be executed while opening the {@linkplain Inventory}
     * <p>
     * See {@linkplain OpenMenuAction}
     *
     * @param openMenuAction Simple action that will be execute while opening the menu.
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder openEvent(OpenMenuAction openMenuAction);

    /**
     * Adds an action that will be executed while closing the {@linkplain Inventory}
     * <p>
     * See {@linkplain CloseMenuAction}
     *
     * @param closeMenuAction Simple action that will be execute while closing the menu.
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder closeEvent(CloseMenuAction closeMenuAction);

    /**
     * Sets the value of cancel click, that will be used if a player try to click at any slot of the {@linkplain Inventory}
     *
     * @param cancelClick Value to cancel clicking.
     * @return The same mutable instance of MenuBuilder
     */
    MenuBuilder cancelClick(boolean cancelClick);

    /**
     * Builds the final instance of {@link Inventory} with all above attributes.
     *
     * @return A non null {@linkplain Inventory} instance with all the above attributes.
     */
    Inventory build();

    /**
     * Creates a new instance of {@link MenuBuilder} with the specified title.
     *
     * @param title The title of the {@linkplain Inventory}
     * @return A new instance of {@linkplain MenuBuilder}
     */
    static MenuBuilder newBuilder(String title) {
        return new DefaultMenuBuilder(title);
    }

    /**
     * Creates a new instance of {@link MenuBuilder} with the specified title and rows.
     *
     * @param title The title of the {@linkplain Inventory}
     * @param rows The rows of the {@linkplain Inventory}
     * @return A new instance of {@linkplain MenuBuilder}
     */
    static MenuBuilder newBuilder(String title, int rows) {
        return new DefaultMenuBuilder(title, rows);
    }

}