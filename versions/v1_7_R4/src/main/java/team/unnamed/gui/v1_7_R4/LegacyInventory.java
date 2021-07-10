package team.unnamed.gui.v1_7_R4;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Method;

/**
 * Created by Ryzeon
 * Project: gui
 * Date: 10/07/2021 @ 03:45
 * Twitter: @Ryzeon_ 😎
 * Github:  github.ryzeon.me
 */
public class LegacyInventory {

	protected static Method customInvMethod;

	public static void openCustomInventory(Player player, Inventory inventory) {
		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
		try {
			getCustomOpenInventoryMethod().invoke(player, inventory, entityPlayer, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static Method getCustomOpenInventoryMethod() {
		if (customInvMethod == null)
			try {
				customInvMethod = CraftHumanEntity.class.getDeclaredMethod("openCustomInventory", Inventory.class, EntityPlayer.class, int.class);
				customInvMethod.setAccessible(true);
			} catch (NoSuchMethodException ex) {
				ex.printStackTrace();
			}
		return customInvMethod;
	}
}
