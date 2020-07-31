package team.unnamed.gui.event.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuClickEvent extends MenuEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int clickedSlot;
    private final ItemStack item;

    public MenuClickEvent(Player who, Inventory inventory, int clickedSlot, ItemStack item) {
        super(who, inventory);
        this.clickedSlot = clickedSlot;
        this.item = item;
    }

    public int getClickedSlot() {
        return clickedSlot;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}