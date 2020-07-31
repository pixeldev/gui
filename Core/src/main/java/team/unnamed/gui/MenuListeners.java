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

import team.unnamed.gui.event.api.MenuClickEvent;
import team.unnamed.gui.event.api.MenuCloseEvent;
import team.unnamed.gui.event.api.MenuOpenEvent;
import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.MenuBuilder;

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
            Inventory defaultMenuInventoryHolderImpl = (Inventory) inventory.getHolder();

            defaultMenuInventoryHolderImpl.getMenuBuilder().getOpenMenuAction().ifPresent(openMenuAction -> event.setCancelled(openMenuAction.executeOpen(event)));

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
            InventoryHolderImpl defaultMenuInventoryHolderImpl = (InventoryHolderImpl) inventory.getHolder();

            defaultMenuInventoryHolderImpl.getMenuBuilder().getCloseMenuAction().ifPresent(closeMenuAction -> closeMenuAction.executeClose(event));

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

            InventoryHolderImpl defaultMenuInventoryHolderImpl = (InventoryHolderImpl) inventory.getHolder();

            MenuBuilder menuBuilder = defaultMenuInventoryHolderImpl.getMenuBuilder();

            Optional<ItemClickable> itemClickableOptional = menuBuilder.getItemClickable(slot);

            if (itemClickableOptional.isPresent()) {
                event.setCancelled(itemClickableOptional.get().getButton().executeClick(event));
            } else {
                if (menuBuilder.getItemToFill().isPresent() && isClickable(menuBuilder, slot)) {
                    event.setCancelled(menuBuilder.getItemToFill().get().getButton().executeClick(event));
                } else {
                    event.setCancelled(menuBuilder.isCancelClick());
                }
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

        return inventory.getHolder() instanceof InventoryHolderImpl;
    }

    private boolean isClickable(MenuBuilder menuBuilder, int input) {
        return menuBuilder.getFrom() != -1 && menuBuilder.getTo() != -1 && input >= menuBuilder.getFrom() && input <= menuBuilder.getTo();
    }

}