package team.unnamed.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class DefaultMenuHolder implements InventoryHolder {

    private final MenuBuilder menuBuilder;

    public DefaultMenuHolder(MenuBuilder menuBuilder) {
        this.menuBuilder = menuBuilder;
    }

    public MenuBuilder getMenuBuilder() {
        return menuBuilder;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, menuBuilder.getRows() * 9, menuBuilder.getTitle());

        menuBuilder.getItemToFill().ifPresent(itemClickable -> {
            if(menuBuilder.getFrom() != -1 && menuBuilder.getTo() != -1) {
                for(int fill = menuBuilder.getFrom(); fill <= menuBuilder.getTo(); fill++) {
                    inventory.setItem(fill, itemClickable.getItem());
                }
            }
        });

        menuBuilder.getItems().forEach((slot, itemClickable) -> inventory.setItem(slot, itemClickable.getItem()));

        return inventory;
    }

}