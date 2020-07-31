package team.unnamed.gui.item;

import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.button.Button;

public class DefaultItemClickable implements ItemClickable {

  private final int slot;
  private final ItemStack item;
  private final Button button;

  public DefaultItemClickable(int slot, ItemStack item, Button button) {
    this.slot = slot;
    this.item = item;
    this.button = button;
  }

  @Override
  public int getSlot() {
    return slot;
  }

  @Override
  public ItemStack getItem() {
    return item;
  }

  @Override
  public Button getButton() {
    return button;
  }
}