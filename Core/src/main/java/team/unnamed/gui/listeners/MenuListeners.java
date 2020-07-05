package team.unnamed.gui.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.MenuBuilder;
import team.unnamed.gui.menu.MenuManager;

import java.util.concurrent.atomic.AtomicReference;

public class MenuListeners implements Listener {

    private final MenuManager menuManager;

    public MenuListeners(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        MenuBuilder menuBuilder = getMenu(event);

        if(menuBuilder == null) {
            return;
        }

        menuManager.getMenuBuilders().remove(menuBuilder);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        MenuBuilder menuBuilder = getMenu(event);

        if(menuBuilder == null) {
            return;
        }

        menuBuilder.getButtons().forEach(simpleButton -> {
            if(simpleButton.getSlot() == event.getSlot()) {
                event.setCancelled(simpleButton.getButton().clickEvent(event));
            }
        });
    }

    private MenuBuilder getMenu(InventoryEvent event) {
        AtomicReference<MenuBuilder> builder = new AtomicReference<>();

        menuManager.getMenuBuilders().forEach(menuBuilder -> {
            if (ChatColor.stripColor(menuBuilder.getTitle()).equals(ChatColor.stripColor(event.getView().getTitle()))) {
                builder.set(menuBuilder);
            }
        });

        return builder.get();
    }

}