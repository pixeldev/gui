package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface ItemClickableAction {

    ItemClickableAction CANCEL_CLICK = new SingleClickableAction(inventory -> true);

    @Nullable Predicate<InventoryClickEvent> getAction(ClickType clickType);

    static ItemClickableActionBuilder builder() {
        return new ItemClickableActionBuilder();
    }

    static ItemClickableAction single(Predicate<InventoryClickEvent> action) {
        return new SingleClickableAction(action);
    }

}
