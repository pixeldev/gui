package team.unnamed.gui.event.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class MenuOpenEvent extends MenuEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public MenuOpenEvent(Player who, Inventory inventory) {
        super(who, inventory);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}