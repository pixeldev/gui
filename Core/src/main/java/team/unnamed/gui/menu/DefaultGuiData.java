package team.unnamed.gui.menu;

import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultGuiData implements GuiData {
    private final String title;
    private final int rows;

    private final Map<Integer, ItemClickable> items = new HashMap<>();

    private final ItemClickable itemClickable;
    private final OpenMenuAction openMenuAction;
    private final CloseMenuAction closeMenuAction;

    private final int from = -1;
    private final int to = -1;

    private final boolean cancelClick = true;

    DefaultGuiData(String title, int rows, ItemClickable itemClickable, OpenMenuAction openMenuAction, CloseMenuAction closeMenuAction) {
        this.title = title;
        this.rows = rows;
        this.itemClickable = itemClickable;
        this.openMenuAction = openMenuAction;
        this.closeMenuAction = closeMenuAction;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public Map<Integer, ItemClickable> getItems() {
        return items;
    }

    @Override
    public Optional<OpenMenuAction> getOpenMenuAction() {
        return Optional.ofNullable(openMenuAction);
    }

    @Override
    public Optional<CloseMenuAction> getCloseMenuAction() {
        return Optional.ofNullable(closeMenuAction);
    }

    @Override
    public Optional<ItemClickable> getItemToFill() {
        return Optional.ofNullable(itemClickable);
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }

    @Override
    public boolean isCancelClick() {
        return cancelClick;
    }

}
