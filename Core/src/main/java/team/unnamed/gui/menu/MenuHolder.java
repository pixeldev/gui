package team.unnamed.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuHolder implements InventoryHolder {

    private final MenuBuilder menuBuilder;

    public MenuHolder(MenuBuilder menuBuilder) {
        this.menuBuilder = menuBuilder;
    }

    public MenuBuilder getMenuBuilder() {
        return menuBuilder;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, menuBuilder.getRows() * 9, menuBuilder.getTitle());

        menuBuilder.getItems().forEach(inventory::setItem);

        return inventory;
    }

}