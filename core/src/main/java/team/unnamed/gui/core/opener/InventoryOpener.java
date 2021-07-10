package team.unnamed.gui.core.opener;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Method;

import static team.unnamed.gui.core.version.ServerVersionProvider.SERVER_VERSION_INT;

public class InventoryOpener {

	protected static Method legacyMethod;

	static {
		try {
			Class<?> legacy = Class.forName("team.unnamed.gui.v1_7_R4.LegacyInventory");
			legacyMethod = legacy.getDeclaredMethod("openCustomInventory", Player.class, Inventory.class);
		} catch (NoSuchMethodException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static void open(Player player, Inventory inventory) {
		if (SERVER_VERSION_INT != 7) {
			player.openInventory(inventory);
		} else {
			boolean accessible = legacyMethod.isAccessible();

			try {
				legacyMethod.setAccessible(true);
				legacyMethod.invoke(null, player, inventory);
			} catch (Exception e) {
				throw new IllegalArgumentException("An error has occurred while opening inventory " + inventory.getTitle(), e);
			} finally {
				legacyMethod.setAccessible(accessible);
			}
		}
	}
}
