package team.unnamed.gui.menu.factory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import team.unnamed.gui.api.menu.MenuData;
import team.unnamed.gui.api.menu.MenuInventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MenuFactory {

    private static final String SERVER_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

    private static final Class<?> MENU_CLASS;
    private static final Constructor<?> MENU_CONSTRUCTOR;

    static {
        try {
            MENU_CLASS = Class.forName("team.unnamed.gui.v" + SERVER_VERSION + ".CraftMenuInventory" + SERVER_VERSION);

            MENU_CONSTRUCTOR = MENU_CLASS.getConstructor(InventoryHolder.class, int.class, String.class, MenuData.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException("You server version isn't supported to UnnamedGUI!");
        }
    }

    public static Inventory get(int size, String title, MenuData data) {
        try {
            MenuInventory menuInventory = (MenuInventory) MENU_CONSTRUCTOR
                    .newInstance(null, size, title, data);

            return menuInventory.getMenuInventory();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}