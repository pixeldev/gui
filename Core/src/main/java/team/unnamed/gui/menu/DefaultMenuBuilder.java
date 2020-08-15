package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.api.item.ItemClickable;
import team.unnamed.gui.api.menu.MenuData;
import team.unnamed.gui.api.menu.action.CloseMenuAction;
import team.unnamed.gui.api.menu.action.OpenMenuAction;
import team.unnamed.gui.menu.factory.MenuFactory;
import team.unnamed.gui.item.DefaultItemClickable;

import java.util.Arrays;
import java.util.List;

public class DefaultMenuBuilder implements MenuBuilder {

    private final String title;
    private int rows;

    private ItemClickable[] items;

    private OpenMenuAction openMenuAction;
    private CloseMenuAction closeMenuAction;

    private boolean cancelClick = true;

    DefaultMenuBuilder(String title) {
        this(title, 6);
    }

    DefaultMenuBuilder(String title, int rows) {
        this.title = title;
        this.rows = rows;

        items = new ItemClickable[rows * 9];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder setRows(int rows) {
        this.rows = rows;
        items = Arrays.copyOfRange(items, 0, rows * 9);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder fillItem(ItemClickable itemClickable, int from, int to) {
        for (int i = from; i < to; i++) {
            items[i] = new DefaultItemClickable(i, itemClickable.getItem(), itemClickable.getButton());
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder setItems(List<ItemClickable> items) {
        this.items = items.toArray(new ItemClickable[0]);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder addItem(ItemClickable itemClickable) {
        items[itemClickable.getSlot()] = itemClickable;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder openEvent(OpenMenuAction openMenuAction) {
        this.openMenuAction = openMenuAction;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder closeEvent(CloseMenuAction closeMenuAction) {
        this.closeMenuAction = closeMenuAction;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuBuilder cancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory build() {
        MenuData guiData = new DefaultMenuData(title, rows, Arrays.asList(items), openMenuAction, closeMenuAction, cancelClick);
        Inventory inventory = MenuFactory.get(rows * 9, title, guiData).getMenuInventory();

        if (inventory == null) {
            throw new NullPointerException("Some error has ocurred while opening menu " + guiData.getTitle());
        }

        for (ItemClickable itemClickable : items) {
            if(itemClickable == null){
                continue;
            }

            inventory.setItem(
                    itemClickable.getSlot(),
                    itemClickable.getItem()
            );
        }
        return inventory;
    }
}