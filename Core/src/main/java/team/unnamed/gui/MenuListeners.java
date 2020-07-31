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

import team.unnamed.gui.event.api.MenuClickEvent;
import team.unnamed.gui.event.api.MenuCloseEvent;
import team.unnamed.gui.event.api.MenuOpenEvent;
import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.GuiData;
import team.unnamed.gui.menu.GuiDataHolder;
import team.unnamed.gui.menu.InventoryGui;

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
            GuiDataHolder guiDataHolder = (GuiDataHolder) inventory.getHolder();
            GuiData guiData = guiDataHolder.getData();

            guiData.getOpenMenuAction().ifPresent(openMenuAction -> event.setCancelled(openMenuAction.executeOpen(event)));

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
            GuiDataHolder guiDataHolder = (GuiDataHolder) inventory.getHolder();
            GuiData guiData = guiDataHolder.getData();

            guiData.getCloseMenuAction().ifPresent(closeMenuAction -> closeMenuAction.executeClose(event));

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

            GuiDataHolder guiDataHolder = (GuiDataHolder) inventory.getHolder();
            GuiData guiData = guiDataHolder.getData();

            Optional<ItemClickable> itemClickableOptional = guiData.getItemClickable(slot);

            if (itemClickableOptional.isPresent()) {
                event.setCancelled(itemClickableOptional.get().getButton().executeClick(event));
            } else {
                if (guiData.getItemToFill().isPresent() && isClickable(guiData, slot)) {
                    event.setCancelled(guiData.getItemToFill().get().getButton().executeClick(event));
                } else {
                    event.setCancelled(guiData.isCancelClick());
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

        InventoryHolder holder = inventory.getHolder();

        // Fuck 'u bukkit
        return inventory instanceof InventoryGui || holder.equals(holder.getInventory());
    }

    private boolean isClickable(GuiData guiData, int input) {
        return guiData.getFrom() != -1 && guiData.getTo() != -1 && input >= guiData.getFrom() && input <= guiData.getTo();
    }

}