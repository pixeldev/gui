package team.unnamed.gui.api.item;

import org.bukkit.inventory.ItemStack;

/**
 * {@inheritDoc}
 */
class DefaultItemClickable implements ItemClickable {

    private final int slot;
    private final ItemStack item;
    private final Button button;

    protected DefaultItemClickable(int slot, ItemStack item, Button button) {
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