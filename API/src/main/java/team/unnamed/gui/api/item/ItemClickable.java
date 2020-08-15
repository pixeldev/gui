package team.unnamed.gui.api.item;

import org.bukkit.inventory.ItemStack;

/**
 * Represents an {@link ItemStack} that can be clicked and is associated with a slot in a Menu
 */
public interface ItemClickable {

    /**
     * The slot in which the item returned by {@linkplain ItemClickable#getSlot()} can be found
     *
     * @return The slot for this instance
     */
    int getSlot();

    /**
     * The {@link ItemStack} showed on the menu
     *
     * @return A non null {@linkplain ItemStack} instance
     */
    ItemStack getItem();

    /**
     * The action executed when this item instance is clicked
     *
     * @see Button
     * @return A non null {@link Button} instance
     */
    Button getButton();

}