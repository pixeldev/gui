package team.unnamed.gui.button;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface Button {

    boolean executeClick(InventoryClickEvent event);

}