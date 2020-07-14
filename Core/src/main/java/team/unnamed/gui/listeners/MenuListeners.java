package team.unnamed.gui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.button.Button;
import team.unnamed.gui.button.SimpleButton;
import team.unnamed.gui.menu.MenuHolder;

import java.util.Map;

public class MenuListeners implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (isInventory(event.getInventory())) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) event.getInventory().getHolder();

        if (menuHolder.getMenuBuilder().getOpenMenuEvent() == null) {
            return;
        }

        event.setCancelled(menuHolder.getMenuBuilder().getOpenMenuEvent().openEvent(event));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (isInventory(event.getInventory())) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) event.getInventory().getHolder();

        for(Map.Entry<Integer, SimpleButton> buttons : menuHolder.getMenuBuilder().getButtons().entrySet()) {
            if (event.getSlot() == buttons.getKey()) {
                event.setCancelled(buttons.getValue().getButton().clickEvent(event));

                break;
            }

            event.setCancelled(menuHolder.getMenuBuilder().isCancelFill());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (isInventory(event.getInventory())) {
            return;
        }

        MenuHolder menuHolder = (MenuHolder) event.getInventory().getHolder();

        if (menuHolder.getMenuBuilder().getCloseMenuEvent() == null) {
            return;
        }

        menuHolder.getMenuBuilder().getCloseMenuEvent().closeEvent(event);
    }

    private boolean isInventory(Inventory inventory) {
        if (inventory == null) {
            return true;
        }

        if (inventory.getHolder() == null) {
            return true;
        }

        return !(inventory.getHolder() instanceof MenuHolder);
    }

}
