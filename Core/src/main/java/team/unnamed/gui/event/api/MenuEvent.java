package team.unnamed.gui.event.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;

public class MenuEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Inventory inventory;

    public MenuEvent(Player who, Inventory inventory) {
        super(who);

        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}