package team.unnamed.gui.api.item;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Represents the action executed when an {@link ItemClickable} is clicked
 */
@FunctionalInterface
public interface Button {

    /**
     * Executes the code when an {@link ItemClickable} is clicked
     *
     * @param event The {@link InventoryClickEvent} for this click
     * @return If the event should be cancelled or not
     */
    boolean executeClick(InventoryClickEvent event);

}