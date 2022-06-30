package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.function.Predicate;

public class SingleClickableAction
        implements ItemClickableAction {

    private final Predicate<Inventory> action;

    protected SingleClickableAction(Predicate<Inventory> action) {
        this.action = action;
    }

    @Override
    public Predicate<Inventory> getAction(ClickType clickType) {
        return action;
    }

}
