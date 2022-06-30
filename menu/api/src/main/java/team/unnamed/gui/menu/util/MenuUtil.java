package team.unnamed.gui.menu.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.bukkit.ServerVersion;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.type.MenuInventory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class MenuUtil {

    private static final Constructor<?> WRAPPER_CONSTRUCTOR;

    static {
        try {
            WRAPPER_CONSTRUCTOR = Class.forName(
                    "team.unnamed.gui.menu." + ServerVersion.CURRENT
                            + ".MenuInventoryWrapperImpl"
            ).getConstructor(InventoryHolder.class, MenuInventory.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new ExceptionInInitializerError("Your server version isn't supported for ungui.");
        }
    }

    private MenuUtil() {
        // the class shouldn't be instantiated
        throw new UnsupportedOperationException();
    }

    public static void fillItemList(List<ItemClickable> items, int slots) {
        for (int i = 0; i < slots; i++) {
            items.add(null);
        }
    }

    public static @NotNull Inventory parseToInventory(MenuInventory menuInventory) {
        try {
            MenuInventoryWrapper wrapper
                    = (MenuInventoryWrapper) WRAPPER_CONSTRUCTOR.newInstance(
                    null, menuInventory);

            return wrapper.getRawInventory();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ExceptionInInitializerError(
                    "An error has occurred while creating menu "
                            + menuInventory.getTitle());
        }
    }

    public static boolean isCustomMenu(Inventory inventory) {
        if (inventory == null) {
            return false;
        }

        InventoryHolder holder = inventory.getHolder();

        return holder instanceof MenuInventoryWrapper
                || inventory instanceof MenuInventoryWrapper;
    }

    public static MenuInventoryWrapper getAsWrapper(Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();

        return holder == null ?
                (MenuInventoryWrapper) inventory :
                (MenuInventoryWrapper) holder;
    }

}
