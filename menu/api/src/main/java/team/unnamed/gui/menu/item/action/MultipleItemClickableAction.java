package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.function.Predicate;

public class MultipleItemClickableAction
        implements ItemClickableAction {

    private final Map<ClickType, Predicate<Inventory>> actions;

    public MultipleItemClickableAction(Map<ClickType, Predicate<Inventory>> actions) {
        this.actions = actions;
    }

    @Override
    public Predicate<Inventory> getAction(ClickType clickType) {
        return actions.get(clickType);
    }

}
