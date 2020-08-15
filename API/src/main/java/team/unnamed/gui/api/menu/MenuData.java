package team.unnamed.gui.api.menu;

import team.unnamed.gui.api.item.ItemClickable;
import team.unnamed.gui.api.menu.action.CloseMenuAction;
import team.unnamed.gui.api.menu.action.OpenMenuAction;

import java.util.List;
import java.util.Optional;

public interface MenuData {

    String getTitle();

    int getRows();

    List<ItemClickable> getItems();

    Optional<OpenMenuAction> getOpenMenuAction();

    Optional<CloseMenuAction> getCloseMenuAction();

    boolean isCancelClick();

    default Optional<ItemClickable> getItemClickable(int slot) {
        return Optional.ofNullable(getItems().get(slot));
    }

}