package team.unnamed.gui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.MenuHolder;

public class MenuListeners implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if(!isInventory(event.getInventory())) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) event.getInventory().getHolder();

        if(menuHolder.getMenuBuilder().getOpenMenuEvent() == null) {
            return;
        }

        event.setCancelled(menuHolder.getMenuBuilder().getOpenMenuEvent().openEvent(event));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!isInventory(event.getInventory())) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) event.getInventory().getHolder();

        menuHolder.getMenuBuilder().getButtons().forEach(simpleButton -> {
            if(simpleButton.getSlot() == event.getSlot()) {
                event.setCancelled(simpleButton.getButton().clickEvent(event));
            }
        });
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if(!isInventory(event.getInventory())) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) event.getInventory().getHolder();

        menuHolder.getMenuBuilder().getCloseMenuEvent().closeEvent(event);
    }

    private boolean isInventory(Inventory inventory) {
        if (inventory == null) {
            return false;
        }

        if (!(inventory.getHolder() instanceof MenuHolder)) {
            return false;
        }

        MenuHolder menuHolder = (MenuHolder) inventory.getHolder();

        return menuHolder != null;
    }

}