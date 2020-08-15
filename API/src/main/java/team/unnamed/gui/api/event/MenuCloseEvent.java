package team.unnamed.gui.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class MenuCloseEvent extends MenuEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public MenuCloseEvent(Player who, Inventory inventory) {
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