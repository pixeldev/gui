package team.unnamed.gui.menu.v1_18_R2;

import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.type.MenuInventory;

public class MenuInventoryWrapperImpl extends CraftInventoryCustom
        implements MenuInventoryWrapper {

    private final MenuInventory menuInventory;

    public MenuInventoryWrapperImpl(
            InventoryHolder owner,
            MenuInventory menuInventory
    ) {
        super(
                owner,
                menuInventory.getSlots(),
                menuInventory.getTitle()
        );

        this.menuInventory = menuInventory;
    }

    @Override
    public @NotNull Inventory getRawInventory() {
        return this;
    }

    @Override
    public @NotNull MenuInventory getMenuInventory() {
        return menuInventory;
    }

}
