package team.unnamed.gui.api.menu.action;

import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface CloseMenuAction {

    /**
     * A simple action that will be executed while closing the menu
     *
     * @param event Event executed while closing the menu
     */
    void executeClose(InventoryCloseEvent event);

}