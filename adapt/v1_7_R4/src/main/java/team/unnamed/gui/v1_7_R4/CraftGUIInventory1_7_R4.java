package team.unnamed.gui.v1_7_R4;

import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import team.unnamed.gui.abstraction.menu.GUIData;
import team.unnamed.gui.abstraction.menu.GUIInventory;

public class CraftGUIInventory1_7_R4 extends CraftInventoryCustom implements GUIInventory {

    private final GUIData data;

    public CraftGUIInventory1_7_R4(InventoryHolder owner, GUIData data) {
        super(owner, data.getSlots(), data.getTitle());
        this.data = data;
    }

    @Override
    public Inventory getMenuInventory() {
        return this;
    }

    @Override
    public GUIData getData() {
        return data;
    }

}