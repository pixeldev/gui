package team.unnamed.gui.menu;

import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;
import team.unnamed.gui.item.ItemClickable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultMenuBuilder implements MenuBuilder {

    private final String title;
    private int rows;

    private Map<Integer, ItemClickable> items = new HashMap<>();

    private ItemClickable itemClickable;
    private OpenMenuAction openMenuAction;
    private CloseMenuAction closeMenuAction;

    private int from = -1;
    private int to = -1;

    private boolean cancelClick = true;

    protected DefaultMenuBuilder(String title) {
        this(title, 6);
    }

    protected DefaultMenuBuilder(String title, int rows) {
        this.title = title;
        this.rows = rows;
    }

    @Override
    public MenuBuilder setRows(int rows) {
        this.rows = rows;

        return this;
    }

    @Override
    public MenuBuilder fillItem(ItemClickable itemClickable, int from, int to) {
        this.itemClickable = itemClickable;
        this.from = from;
        this.to = to;

        return this;
    }

    @Override
    public MenuBuilder setItems(Map<Integer, ItemClickable> items) {
        this.items = items;

        return this;
    }

    @Override
    public MenuBuilder addItem(int slot, ItemClickable itemClickable) {
        items.put(slot, itemClickable);

        return this;
    }

    @Override
    public MenuBuilder openEvent(OpenMenuAction openMenuAction) {
        this.openMenuAction = openMenuAction;

        return this;
    }

    @Override
    public MenuBuilder closeEvent(CloseMenuAction closeMenuAction) {
        this.closeMenuAction = closeMenuAction;

        return this;
    }

    @Override
    public MenuBuilder cancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;

        return this;
    }
}