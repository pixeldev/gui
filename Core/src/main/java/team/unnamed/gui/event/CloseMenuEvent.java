package team.unnamed.gui.event;

import org.bukkit.event.inventory.InventoryCloseEvent;

@FunctionalInterface
public interface CloseMenuEvent {

    void closeEvent(InventoryCloseEvent event);

}