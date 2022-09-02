package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Map;
import java.util.function.Predicate;

public class MultipleItemClickableAction
        implements ItemClickableAction {

    private final Map<ClickType, Predicate<InventoryClickEvent>> actions;

    public MultipleItemClickableAction(Map<ClickType, Predicate<InventoryClickEvent>> actions) {
        this.actions = actions;
    }

    @Override
    public Predicate<InventoryClickEvent> getAction(ClickType clickType) {
        return actions.get(clickType);
    }

}
