package team.unnamed.gui.menu.item;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.menu.item.action.ItemClickableAction;
import team.unnamed.gui.menu.item.action.ItemClickableActionBuilder;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static team.unnamed.validate.Validate.isNotNull;

public class ItemClickableBuilder {

    private final int slot;
    private ItemStack item;
    private ItemClickableAction action;

    protected ItemClickableBuilder(int slot) {
        this.slot = slot;
    }

    public ItemClickableBuilder item(ItemStack item) {
        this.item = isNotNull(item, "Item cannot be null.");
        return this;
    }

    public ItemClickableBuilder multipleAction(Consumer<ItemClickableActionBuilder.Multiple> action) {
        ItemClickableActionBuilder.Multiple actionBuilder = ItemClickableAction.builder()
                .multipleAction();
        action.accept(actionBuilder);
        this.action = actionBuilder.build();
        return this;
    }

    public ItemClickableBuilder action(Predicate<Inventory> action) {
        isNotNull(action, "Action cannot be null.");
        this.action = ItemClickableAction.single(action);
        return this;
    }

    public ItemClickable build() {
        return ItemClickable.of(this.slot, this.item, this.action);
    }

}
