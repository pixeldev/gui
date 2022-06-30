package team.unnamed.gui.v1_7_R4;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Method;

public class LegacyInventory {

    protected static Method customInvMethod;

    static {
        try {
            customInvMethod = CraftHumanEntity.class.getDeclaredMethod("openCustomInventory", Inventory.class, EntityPlayer.class, int.class);
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }

    public static void openCustomInventory(Player player, Inventory inventory) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        boolean accessible = customInvMethod.isAccessible();

        try {
            customInvMethod.setAccessible(true);
            customInvMethod.invoke(player, inventory, entityPlayer, 0);
        } catch (Exception e) {
            throw new IllegalArgumentException("An error has occurred while opening inventory " + inventory.getTitle(), e);
        } finally {
            customInvMethod.setAccessible(accessible);
        }
    }

}
