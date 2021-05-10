package team.unnamed.gui.core.gui.type;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.abstraction.menu.GUIData;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleGUIData implements GUIData {

    protected final String title;
    protected final int slots;

    protected final List<ItemClickable> items;

    protected final Predicate<InventoryOpenEvent> openAction;
    protected final Consumer<InventoryCloseEvent> closeAction;

    protected final boolean cancelClick;

    protected SimpleGUIData(String title,
                            int slots,
                            List<ItemClickable> items,
                            Predicate<InventoryOpenEvent> openAction,
                            Consumer<InventoryCloseEvent> closeAction,
                            boolean cancelClick) {
        this.title = title;
        this.slots = slots;
        this.items = items;
        this.openAction = openAction;
        this.closeAction = closeAction;
        this.cancelClick = cancelClick;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    @Override
    public List<ItemClickable> getItems() {
        return items;
    }

    public Optional<Predicate<InventoryOpenEvent>> getOpenAction() {
        return Optional.ofNullable(openAction);
    }

    public Optional<Consumer<InventoryCloseEvent>> getCloseAction() {
        return Optional.ofNullable(closeAction);
    }

    public boolean isCancelledClick() {
        return cancelClick;
    }

}