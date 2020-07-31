package team.unnamed.gui.menu.action;

import org.bukkit.event.inventory.InventoryOpenEvent;

@FunctionalInterface
public interface OpenMenuAction {

    boolean executeOpen(InventoryOpenEvent event);

}