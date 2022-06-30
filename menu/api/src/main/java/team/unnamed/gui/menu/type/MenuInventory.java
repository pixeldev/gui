package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.unnamed.gui.menu.item.ItemClickable;

import java.util.List;
import java.util.function.Predicate;

public interface MenuInventory {

    static MenuInventoryBuilder newBuilder(String title) {
        return new DefaultMenuInventoryBuilder(title);
    }

    static MenuInventoryBuilder newBuilder(String title, int rows) {
        return new DefaultMenuInventoryBuilder(title, rows);
    }

    static StringLayoutMenuInventoryBuilder newStringLayoutBuilder(String title) {
        return new StringLayoutMenuInventoryBuilder(title);
    }

    static StringLayoutMenuInventoryBuilder newStringLayoutBuilder(String title, int rows) {
        return new StringLayoutMenuInventoryBuilder(title, rows);
    }

    static <E> PaginatedMenuInventoryBuilder<E> newPaginatedBuilder(Class<E> entityType, String title) {
        return new PaginatedMenuInventoryBuilder<>(title);
    }

    static <E> PaginatedMenuInventoryBuilder<E> newPaginatedBuilder(Class<E> entityType, String title, int rows) {
        return new PaginatedMenuInventoryBuilder<>(title, rows);
    }

    @NotNull
    String getTitle();

    int getSlots();

    @NotNull
    List<ItemClickable> getItems();

    void clearItems();

    void setItem(ItemClickable item);

    void removeItem(int slot);

    @Nullable Predicate<Inventory> getOpenAction();

    @Nullable Predicate<Inventory> getCloseAction();

    boolean canIntroduceItems();

    default @Nullable ItemClickable getItem(int slot) {
        return getItems().get(slot);
    }

}
