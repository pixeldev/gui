package team.unnamed.gui.menu.factory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryHolder;

import team.unnamed.gui.api.menu.MenuData;
import team.unnamed.gui.api.menu.MenuInventory;

import java.lang.reflect.InvocationTargetException;

public class MenuFactory {

    private static final String SERVER_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

    private static final Class<?> MENU_CLASS;

    static {
        try {
            MENU_CLASS = Class.forName("team.unnamed.gui.v" + SERVER_VERSION + ".CraftMenuInventory" + SERVER_VERSION);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("You server version isn't supported to UnnamedGUI!");
        }
    }

    public static MenuInventory get(int size, String title, MenuData data) {
        try {
            return (MenuInventory) MENU_CLASS
                    .getConstructor(InventoryHolder.class, int.class, String.class, MenuData.class)
                    .newInstance(null, size, title, data);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}