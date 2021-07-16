package team.unnamed.gui.core;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.abstraction.menu.GUIData;
import team.unnamed.gui.abstraction.menu.GUIInventory;
import team.unnamed.gui.core.gui.factory.GUIFactory;
import team.unnamed.gui.core.gui.type.PaginatedGUIData;

import java.util.Optional;

public class GUIListeners implements Listener {

  private final static Class<?> GUI_REFERENCE = GUIInventory.class;

  @EventHandler
  public void onOpen(InventoryOpenEvent event) {
    Inventory inventory = event.getInventory();
    HumanEntity humanEntity = event.getPlayer();

    if (!(humanEntity instanceof Player)) {
      return;
    }

    if (isGui(inventory)) {
      GUIInventory guiInventory = getGui(inventory);
      GUIData guiData = guiInventory.getData();

      guiData
          .getOpenAction()
          .ifPresent(action -> event.setCancelled(action.test(event)));
    }
  }

  @EventHandler
  public void onClose(InventoryCloseEvent event) {
    Inventory inventory = event.getInventory();
    HumanEntity humanEntity = event.getPlayer();

    if (!(humanEntity instanceof Player)) {
      return;
    }

    if (isGui(inventory)) {
      GUIInventory guiInventory = getGui(inventory);
      GUIData guiData = guiInventory.getData();

      guiData
          .getCloseAction()
          .ifPresent(action -> action.accept(event));
    }
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    Inventory inventory = event.getClickedInventory();

    HumanEntity humanEntity = event.getWhoClicked();

    if (!(humanEntity instanceof Player)) {
      return;
    }

    if (isGui(inventory)) {
      int slot = event.getSlot();

      if (slot < 0) { //Player has clicked outside of inventory.
        return;
      }

      GUIInventory guiInventory = getGui(inventory);

      GUIData guiData = guiInventory.getData();
      Optional<ItemClickable> itemClickableOptional = guiData.getItemClickable(slot);

      boolean cancelClick = guiData.isCancelledClick();

      if (!itemClickableOptional.isPresent()) {
        event.setCancelled(cancelClick);

        return;
      }

      ItemClickable itemClickable = itemClickableOptional.get();

      if (event.getRawSlot() != slot && cancelClick) { //Check if clicked slot isn't an item clickable and click is cancelled.
        event.setCancelled(true);

        return;
      }

      if (guiData instanceof PaginatedGUIData) {
        PaginatedGUIData<?> paginatedGUIData = (PaginatedGUIData<?>) guiData;
        int currentPage = paginatedGUIData.getCurrentPage();

        PaginatedGUIData<?> newPaginatedGUIData = null;

        ItemStack clickedItem = event.getCurrentItem();

        ItemClickable nextPageItem = paginatedGUIData.getNextPageItem().apply(currentPage + 1);
        ItemClickable previousPageItem = paginatedGUIData.getPreviousPageItem().apply(currentPage - 1);

        int nextPageItemSlot = nextPageItem.getSlot();
        int previousPageItemSlot = previousPageItem.getSlot();

        if (
            nextPageItem.getItemStack().equals(clickedItem) &&
                slot == nextPageItemSlot
        ) {
          event.setCancelled(true);

          currentPage++;
        } else if (
            previousPageItem.getItemStack().equals(clickedItem) &&
                slot == previousPageItemSlot
        ) {
          event.setCancelled(true);

          currentPage--;
        }

        if (currentPage != paginatedGUIData.getCurrentPage()) {
          newPaginatedGUIData = paginatedGUIData.createNewDataFromPage(currentPage);
        }

        if (newPaginatedGUIData != null) {
          humanEntity.openInventory(PaginatedGUICreator.createPage(
              GUIFactory.create(newPaginatedGUIData),
              newPaginatedGUIData
          ));

          return;
        }
      }

      event.setCancelled(itemClickable.getAction().test(event));
    }
  }

  private boolean isGui(Inventory inventory) {
    if (inventory == null) {
      return false;
    }

    InventoryHolder holder = inventory.getHolder();
    boolean instanceOf = inventory.getClass().getPackage().equals(GUI_REFERENCE.getPackage());
    return holder == null ?
        instanceOf :
        instanceOf || holder.equals(holder.getInventory());
  }

  private GUIInventory getGui(Inventory inventory) {
    InventoryHolder holder = inventory.getHolder();

    return holder == null ?
        (GUIInventory) inventory :
        (GUIInventory) inventory.getHolder();
  }

}