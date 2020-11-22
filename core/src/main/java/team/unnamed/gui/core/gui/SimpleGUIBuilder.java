package team.unnamed.gui.core.gui;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.abstraction.menu.GUIData;
import team.unnamed.gui.core.gui.factory.GUIFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static team.unnamed.validate.Validate.notNull;

public class SimpleGUIBuilder implements GUIBuilder {

    private final String title;
    private final int slots;

    private ItemClickable[] items;

    private Predicate<InventoryOpenEvent> openAction;
    private Consumer<InventoryCloseEvent> closeAction;

    private boolean cancelClick = true;

    protected SimpleGUIBuilder(String title) {
        this(title, 6);
    }

    protected SimpleGUIBuilder(String title, int rows) {
        this.title = title;
        this.slots = rows * 9;

        items = new ItemClickable[this.slots];
    }

    @Override
    public GUIBuilder fillItem(ItemClickable item, int from, int to) {
        notNull(item, "Item clickable can't be null");

        for (int i = from; i < to; i++) {
            items[i] = item;
        }

        return this;
    }

    @Override
    public GUIBuilder setItems(List<ItemClickable> items) {
        notNull(items, "Items can't be null.");

        this.items = items.toArray(new ItemClickable[0]);

        return this;
    }

    @Override
    public GUIBuilder addItem(ItemClickable itemClickable) {
        notNull(itemClickable, "Item clickable can't be null.");

        items[itemClickable.getSlot()] = itemClickable;

        return this;
    }

    @Override
    public GUIBuilder openAction(Predicate<InventoryOpenEvent> openAction) {
        this.openAction = openAction;

        return this;
    }

    @Override
    public GUIBuilder closeAction(Consumer<InventoryCloseEvent> closeAction) {
        this.closeAction = closeAction;

        return this;
    }

    @Override
    public GUIBuilder toggleClick() {
        this.cancelClick = !cancelClick;

        return this;
    }

    @Override
    public Inventory build() {
        GUIData guiData = new SimpleGUIData(title, slots, Arrays.asList(items), openAction, closeAction, cancelClick);
        Inventory inventory = GUIFactory.create(guiData);

        if (inventory == null) {
            return null;
        }

        for (ItemClickable itemClickable : items) {
            if (itemClickable == null) {
                continue;
            }

            inventory.setItem(itemClickable.getSlot(), itemClickable.getItemStack());
        }

        return inventory;
    }

}