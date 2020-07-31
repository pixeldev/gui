package team.unnamed.gui.menu;

import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;

import java.util.Map;
import java.util.Optional;

public interface GuiData {

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

}
