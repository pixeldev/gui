package team.unnamed.gui.item;

import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.button.Button;


/**
 * {@inheritDoc}
 */
public class DefaultItemClickable implements ItemClickable {

    private final int slot;
    private final ItemStack item;
    private final Button button;

    public DefaultItemClickable(int slot, ItemStack item, Button button) {
        this.slot = slot;
        this.item = item;
        this.button = button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSlot() {
        return slot;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public ItemStack getItem() {
        return item;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Button getButton() {
        return button;
    }

}