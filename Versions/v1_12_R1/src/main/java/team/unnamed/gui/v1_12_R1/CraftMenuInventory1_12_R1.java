package team.unnamed.gui.v1_12_R1;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import team.unnamed.gui.api.menu.MenuData;
import team.unnamed.gui.api.menu.MenuInventory;

public class CraftMenuInventory1_12_R1 extends CraftInventoryCustom implements MenuInventory {

    private final MenuData data;

    public CraftMenuInventory1_12_R1(InventoryHolder owner, int size, String title, MenuData data) {
        super(owner, size, title);

        this.data = data;
    }

    public MenuData getData() {
        return data;
    }

    @Override
    public Inventory getMenuInventory() {
        return this;
    }

}