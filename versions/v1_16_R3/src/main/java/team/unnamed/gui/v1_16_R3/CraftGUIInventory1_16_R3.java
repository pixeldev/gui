package team.unnamed.gui.v1_16_R3;

import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftInventoryCustom;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import team.unnamed.gui.abstraction.menu.GUIData;
import team.unnamed.gui.abstraction.menu.GUIInventory;

public class CraftGUIInventory1_16_R3 extends CraftInventoryCustom implements GUIInventory {

  private final GUIData guiData;

  public CraftGUIInventory1_16_R3(InventoryHolder owner, GUIData guiData) {
    super(owner, guiData.getSlots(), guiData.getTitle());

    this.guiData = guiData;
  }

  @Override
  public Inventory getMenuInventory() {
    return this;
  }

  @Override
  public GUIData getData() {
    return guiData;
  }

}