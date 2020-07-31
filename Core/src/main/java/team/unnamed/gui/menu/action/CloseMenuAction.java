package team.unnamed.gui.menu.action;

import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface CloseMenuAction {

    void executeClose(InventoryCloseEvent event);

}