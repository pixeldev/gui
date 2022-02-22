package team.unnamed.gui.abstraction.item.nbt;

import org.bukkit.inventory.ItemStack;

public interface ItemStackNBT {

    boolean hasTag(ItemStack itemStack, String key);

    ItemStack removeTag(ItemStack itemStack, String key);

    ItemStack applyTag(ItemStack itemStack, String key);

    ItemStack applyTag(ItemStack itemStack, String key, int value);

    ItemStack applyTag(ItemStack itemStack, String key, String value);

    ItemStack applyTag(ItemStack itemStack, String key, byte value);

    String getTags(ItemStack itemStack);
}
