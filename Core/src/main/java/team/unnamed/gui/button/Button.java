package team.unnamed.gui.button;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Represents the action executed when an {@link team.unnamed.gui.item.ItemClickable} is clicked
 */
@FunctionalInterface
public interface Button {

    /**
     * Executes the code when an {@link team.unnamed.gui.item.ItemClickable} is clicked
     *
     * @param event The {@link InventoryClickEvent} for this click
     * @return If the event should be cancelled or not
     */
    boolean executeClick(InventoryClickEvent event);

}