package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.util.MenuUtil;

import java.util.List;
import java.util.function.Predicate;

public class DefaultMenuInventory implements MenuInventory {

    protected final String title;
    protected final int slots;
    protected final List<ItemClickable> items;
    protected final Predicate<Inventory> openAction;
    protected final Predicate<Inventory> closeAction;
    protected final boolean canIntroduceItems;

    protected DefaultMenuInventory(
            String title, int slots,
            List<ItemClickable> items,
            Predicate<Inventory> openAction,
            Predicate<Inventory> closeAction,
            boolean canIntroduceItems
    ) {
        this.title = title;
        this.slots = slots;
        this.items = items;
        this.openAction = openAction;
        this.closeAction = closeAction;
        this.canIntroduceItems = canIntroduceItems;
    }

    @NotNull
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    @NotNull
    @Override
    public List<ItemClickable> getItems() {
        return items;
    }

    @Override
    public void clearItems() {
        this.items.clear();
        MenuUtil.fillItemList(this.items, this.slots);
    }

    @Override
    public void setItem(ItemClickable item) {
        this.items.set(item.getSlot(), item);
    }

    @Override
    public void removeItem(int slot) {
        this.items.remove(slot);
    }

    @Nullable
    @Override
    public Predicate<Inventory> getOpenAction() {
        return openAction;
    }

    @Nullable
    @Override
    public Predicate<Inventory> getCloseAction() {
        return closeAction;
    }

    @Override
    public boolean canIntroduceItems() {
        return canIntroduceItems;
    }

}
