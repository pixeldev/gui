package team.unnamed.gui.menu;

import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;

import java.util.*;

public class DefaultGuiData implements GuiData {
    private final String title;
    private final int rows;

    private final List<ItemClickable> items;

    private final OpenMenuAction openMenuAction;
    private final CloseMenuAction closeMenuAction;

    private final boolean cancelClick;

    DefaultGuiData(String title, int rows, List<ItemClickable> items, OpenMenuAction openMenuAction, CloseMenuAction closeMenuAction, boolean cancelClick) {
        this.title = title;
        this.rows = rows;
        this.items = items;
        this.openMenuAction = openMenuAction;
        this.closeMenuAction = closeMenuAction;
        this.cancelClick = cancelClick;
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
    public List<ItemClickable> getItems() {
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
    public boolean isCancelClick() {
        return cancelClick;
    }

}