package team.unnamed.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import org.bukkit.inventory.InventoryHolder;
import team.unnamed.gui.api.event.MenuClickEvent;
import team.unnamed.gui.api.event.MenuCloseEvent;
import team.unnamed.gui.api.event.MenuOpenEvent;
import team.unnamed.gui.api.item.ItemClickable;
import team.unnamed.gui.api.menu.MenuData;
import team.unnamed.gui.api.menu.MenuInventory;

import java.util.Optional;

public class MenuListeners implements Listener {

    @EventHandler
    public void openMenuEvent(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        HumanEntity humanEntity = event.getPlayer();

        if (!(humanEntity instanceof Player)) {
            return;
        }

        if (isInventory(inventory)) {
            MenuInventory menuInventory;

            if (inventory.getHolder() == null) {
                menuInventory = (MenuInventory) inventory;
            } else {
                menuInventory = (MenuInventory) inventory.getHolder();
            }

            MenuData data = menuInventory.getData();

            data.getOpenMenuAction().ifPresent(openMenuAction -> event.setCancelled(openMenuAction.executeOpen(event)));

            Bukkit.getPluginManager().callEvent(
                    new MenuOpenEvent(
                            (Player) humanEntity,
                            inventory
                    )
            );
        }
    }

    @EventHandler
    public void closeMenuEvent(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        HumanEntity humanEntity = event.getPlayer();

        if (!(humanEntity instanceof Player)) {
            return;
        }

        if (isInventory(inventory)) {
            MenuInventory menuInventory;

            if (inventory.getHolder() == null) {
                menuInventory = (MenuInventory) inventory;
            } else {
                menuInventory = (MenuInventory) inventory.getHolder();
            }

            MenuData data = menuInventory.getData();

            data.getCloseMenuAction().ifPresent(closeMenuAction -> closeMenuAction.executeClose(event));

            Bukkit.getPluginManager().callEvent(
                    new MenuCloseEvent(
                            (Player) humanEntity,
                            inventory
                    )
            );
        }
    }

    @EventHandler
    public void clickMenuEvent(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        HumanEntity humanEntity = event.getWhoClicked();

        if (!(humanEntity instanceof Player)) {
            return;
        }

        if (isInventory(inventory)) {
            int slot = event.getSlot();

            if (slot < 0) {
                return;
            }

            MenuInventory menuInventory;

            if (inventory.getHolder() == null) {
                menuInventory = (MenuInventory) inventory;
            } else {
                menuInventory = (MenuInventory) inventory.getHolder();
            }

            MenuData data = menuInventory.getData();

            Optional<ItemClickable> itemClickableOptional = data.getItemClickable(slot);

            if (itemClickableOptional.isPresent()) {
                if (event.getRawSlot() != slot && data.isCancelClick()) {
                    event.setCancelled(true);
                    return;
                }

                event.setCancelled(itemClickableOptional.get().getButton().executeClick(event));
            } else {
                event.setCancelled(data.isCancelClick());
            }

            Bukkit.getPluginManager().callEvent(
                    new MenuClickEvent(
                            (Player) humanEntity,
                            inventory,
                            slot,
                            event.getCurrentItem()
                    )
            );
        }
    }

    private boolean isInventory(Inventory inventory) {
        if (inventory == null) {
            return false;
        }

        InventoryHolder holder = inventory.getHolder();

        return holder != null ? holder.equals(inventory) || inventory instanceof MenuInventory : inventory instanceof MenuInventory;
    }

}