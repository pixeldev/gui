package team.unnamed.gui.event;

import org.bukkit.event.inventory.InventoryOpenEvent;

@FunctionalInterface
public interface OpenMenuEvent {

    boolean openEvent(InventoryOpenEvent event);

}