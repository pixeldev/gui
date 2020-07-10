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

        if(menuBuilder.getItemForFill() != null) {
            if(menuBuilder.getFillFrom() != -1 && menuBuilder.getFillTo() != -1) {
                for(int fill = menuBuilder.getFillFrom(); fill <= menuBuilder.getFillTo(); fill++) {
                    inventory.setItem(fill, menuBuilder.getItemForFill());
                }
            }
        }

        menuBuilder.getItems().forEach(inventory::setItem);

        return inventory;
    }

}