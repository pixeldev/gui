package team.unnamed.gui.abstraction.item;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

import static team.unnamed.validate.Validate.notNull;

public class ItemClickableBuilder {

    private final int slot;
    private ItemStack itemStack;
    private Predicate<InventoryClickEvent> action = event -> false;

    public ItemClickableBuilder(int slot) {
        this.slot = slot;
    }

    public ItemClickableBuilder setItemStack(ItemStack itemStack) {
        this.itemStack = notNull(itemStack, "Item can't be null.");

        return this;
    }

    public ItemClickableBuilder setAction(Predicate<InventoryClickEvent> action) {
        this.action = action;

        return this;
    }

    public ItemClickable build() {
        return new ItemClickable(slot, itemStack, action);
    }

}