package team.unnamed.gui.menu;

import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;

import java.util.List;
import java.util.Optional;

public interface GuiData {

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