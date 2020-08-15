package team.unnamed.gui.api.menu.action;

import org.bukkit.event.inventory.InventoryOpenEvent;

@FunctionalInterface
public interface OpenMenuAction {

    /**
     * A simple action that will be executed while opening any menu
     * <p>
     * Remember if you returns true, the event will be canceled, and vice versa
     *
     * @param event Event executed while opening the menu.
     * @return If the event will be cancelled, or not.
     */
    boolean executeOpen(InventoryOpenEvent event);

}