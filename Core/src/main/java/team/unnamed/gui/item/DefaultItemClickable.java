package team.unnamed.gui.item;

import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.button.Button;

public class DefaultItemClickable implements ItemClickable {

    private final ItemStack item;
    private final Button button;

    public DefaultItemClickable(ItemStack item, Button button) {
        this.item = item;
        this.button = button;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public Button getButton() {
        return button;
    }

}