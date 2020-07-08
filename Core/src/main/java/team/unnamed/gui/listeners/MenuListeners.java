package team.unnamed.gui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.MenuHolder;

public class MenuListeners implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if (!(inventory.getHolder() instanceof MenuHolder)) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) inventory.getHolder();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if (!(inventory.getHolder() instanceof MenuHolder)) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) inventory.getHolder();

        if(menuHolder == null) {
            return;
        }

        menuHolder.getMenuBuilder().getButtons().forEach(simpleButton -> {
            if(simpleButton.getSlot() == event.getSlot()) {
                event.setCancelled(simpleButton.getButton().clickEvent(event));
            }
        });
    }

}