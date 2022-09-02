package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Predicate;

public class SingleClickableAction
        implements ItemClickableAction {

    private final Predicate<InventoryClickEvent> action;

    protected SingleClickableAction(Predicate<InventoryClickEvent> action) {
        this.action = action;
    }

    @Override
    public Predicate<InventoryClickEvent> getAction(ClickType clickType) {
        return action;
    }

}
