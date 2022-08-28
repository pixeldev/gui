package team.unnamed.gui.menu.item;

import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.menu.item.action.ItemClickableAction;

public class ItemClickable {

    private final int slot;
    private final ItemStack itemStack;
    private final ItemClickableAction action;

    private ItemClickable(
            int slot, ItemStack itemStack,
            ItemClickableAction action
    ) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.action = action;
    }

    public static ItemClickable onlyItem(ItemStack itemStack) {
        return onlyItem(itemStack, ItemClickableAction.CANCEL_CLICK);
    }

    public static ItemClickable onlyItem(ItemStack itemStack, ItemClickableAction action) {
        return of(-1, itemStack, action);
    }

    public static ItemClickable of(int slot, ItemStack itemStack) {
        return of(slot, itemStack, ItemClickableAction.CANCEL_CLICK);
    }

    public static ItemClickable of(int slot, ItemStack itemStack,
                                   ItemClickableAction action) {
        return new ItemClickable(slot, itemStack, action);
    }

    public static ItemClickableBuilder builder(int slot) {
        return new ItemClickableBuilder(slot);
    }

    public static ItemClickableBuilder builder() {
        return new ItemClickableBuilder(-1);
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemClickableAction getAction() {
        return action;
    }

    public ItemClickable clone(int slot) {
        return new ItemClickable(slot, this.itemStack, this.action);
    }

    public ItemClickable clone(ItemClickableAction action) {
        return new ItemClickable(this.slot, this.itemStack, action);
    }

    public ItemClickable clone(ItemStack itemStack) {
        return new ItemClickable(this.slot, itemStack, this.action);
    }

    @Override
    public String toString() {
        return "ItemClickable{" +
                "slot=" + slot +
                ", itemStack=" + itemStack +
                '}';
    }

}
