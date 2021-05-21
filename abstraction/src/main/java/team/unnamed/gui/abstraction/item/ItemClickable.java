package team.unnamed.gui.abstraction.item;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

public final class ItemClickable {

  private static final Predicate<InventoryClickEvent> CANCELLING
      = event -> true;

  private final int slot;
  private final ItemStack itemStack;

  /*
   * Nullable instance of item action.
   */
  private final Predicate<InventoryClickEvent> action;

  protected ItemClickable(int slot,
                          ItemStack itemStack,
                          Predicate<InventoryClickEvent> action) {
    this.slot = slot;
    this.itemStack = itemStack;
    this.action = action;
  }

  public int getSlot() {
    return slot;
  }

  public ItemStack getItemStack() {
    return itemStack;
  }

  public Predicate<InventoryClickEvent> getAction() {
    return action;
  }

  public ItemClickable cloneInSlot(int slot) {
    return new ItemClickable(slot, itemStack, action);
  }

  public static ItemClickableBuilder builder(int slot) {
    return new ItemClickableBuilder(slot);
  }

  public static ItemClickableBuilder builder() {
    return new ItemClickableBuilder(-1);
  }

  public static ItemClickableBuilder builderCancellingEvent() {
    return builder()
        .setAction(CANCELLING);
  }

  public static ItemClickableBuilder builderCancellingEvent(int slot) {
    return builder(slot)
        .setAction(CANCELLING);
  }

}