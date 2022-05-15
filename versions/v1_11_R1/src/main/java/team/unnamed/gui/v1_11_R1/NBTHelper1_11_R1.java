package team.unnamed.gui.v1_11_R1;

import net.minecraft.server.v1_11_R1.NBTTagByte;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagInt;
import net.minecraft.server.v1_11_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.abstraction.item.nbt.NBTHelper;

public class NBTHelper1_11_R1 implements NBTHelper {

    @Override
    public boolean hasTag(ItemStack itemStack, String key) {
        // Check if both arguments are != null
        if (itemStack == null || key == null) {
            // If one of them is null, then return false
            return false;
        }

        try {
            // Create a NMS copy of the provided Bukkit ItemStack
            net.minecraft.server.v1_11_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);

            // Check if the ItemStack has the NBT tag
            return nmsCopy.hasTag() && nmsCopy.getTag().hasKey(key);
        } catch (Exception ex) {
            // Return false if an exception is thrown
            return false;
        }
    }

    @Override
    public ItemStack removeTag(ItemStack itemStack, String key) {
        // Check if both arguments are != null
        if (itemStack == null || key == null) {
            // If one of them is null, then return the original item
            return itemStack;
        }

        try {
            // Create a NMS copy of the provided Bukkit ItemStack
            net.minecraft.server.v1_11_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);

            // Check if the item has the NBT tag we're about to remove
            if (!nmsCopy.hasTag() || !nmsCopy.getTag().hasKey(key)) {
                // If not, return the original item
                return itemStack;
            }

            // Remove the NBT tag
            nmsCopy.getTag().remove(key);

            // Return a new Bukkit copy
            return CraftItemStack.asBukkitCopy(nmsCopy);
        } catch (Exception ex) {
            // Return the original item in case of an exception
            return itemStack;
        }
    }

    @Override
    public ItemStack applyTag(ItemStack itemStack, String key) {
        // Check if both arguments are != null
        if (itemStack == null || key == null) {
            // If one of them is null, then return the original item
            return itemStack;
        }

        return applyTag(itemStack, key, (byte) 1);
    }

    @Override
    public ItemStack applyTag(ItemStack itemStack, String key, int value) {
        // Check if the item and the NBT tag key != null
        if (itemStack == null || key == null) {
            // If one of them is null, return the original item
            return itemStack;
        }

        try {
            // Create a NMS copy of the Bukkit ItemStack
            net.minecraft.server.v1_11_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);

            // Set tag
            NBTTagCompound tag = nmsCopy.hasTag() ? ((nmsCopy.getTag() == null) ? new NBTTagCompound() : nmsCopy.getTag()) : new NBTTagCompound();
            tag.set(key, new NBTTagInt(value));
            nmsCopy.setTag(tag);

            // Return a new Bukkit copy
            return CraftItemStack.asBukkitCopy(nmsCopy);
        } catch (Exception ex) {
            // Return the original item in case of an exception
            return itemStack;
        }
    }

    @Override
    public ItemStack applyTag(ItemStack itemStack, String key, String value) {
        // Check if the item, the NBT tag key and the value != null
        if (itemStack == null || key == null || value == null) {
            // If one of them is null, return the original item
            return itemStack;
        }

        try {
            // Create a NMS copy of the Bukkit ItemStack
            net.minecraft.server.v1_11_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);

            // Set tag
            NBTTagCompound tag = nmsCopy.hasTag() ? ((nmsCopy.getTag() == null) ? new NBTTagCompound() : nmsCopy.getTag()) : new NBTTagCompound();
            tag.set(key, new NBTTagString(value));
            nmsCopy.setTag(tag);

            // Return a new Bukkit copy
            return CraftItemStack.asBukkitCopy(nmsCopy);
        } catch (Exception ex) {
            // Return the original item in case of an exception
            return itemStack;
        }
    }

    @Override
    public ItemStack applyTag(ItemStack itemStack, String key, byte value) {
        // Check if the item, the NBT tag key and the value != null
        if (itemStack == null || key == null) {
            // If one of them is null, return the original item
            return itemStack;
        }

        try {
            // Create a NMS copy of the Bukkit ItemStack
            net.minecraft.server.v1_11_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);

            // Set tag
            NBTTagCompound tag = nmsCopy.hasTag() ? ((nmsCopy.getTag() == null) ? new NBTTagCompound() : nmsCopy.getTag()) : new NBTTagCompound();
            tag.set(key, new NBTTagByte(value));
            nmsCopy.setTag(tag);

            // Return a new Bukkit copy
            return CraftItemStack.asBukkitCopy(nmsCopy);
        } catch (Exception ex) {
            // Return the original item in case of an exception
            return itemStack;
        }
    }

    @Override
    public String getTags(ItemStack itemStack) {
        // Create a new NMS copy
        net.minecraft.server.v1_11_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);

        // Check if the item has tags
        if(!nmsCopy.hasTag()) {
            return null;
        }

        // Return the NBT tags of the item
        return nmsCopy.getTag().toString();
    }
}
