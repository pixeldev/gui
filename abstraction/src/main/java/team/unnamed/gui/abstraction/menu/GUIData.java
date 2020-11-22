package team.unnamed.gui.abstraction.menu;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import team.unnamed.gui.abstraction.item.ItemClickable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface GUIData {

    String getTitle();

    int getSlots();

    List<ItemClickable> getItems();

    Optional<Predicate<InventoryOpenEvent>> getOpenAction();

    Optional<Consumer<InventoryCloseEvent>> getCloseAction();

    boolean isCancelledClick();

    default Optional<ItemClickable> getItemClickable(int slot) {
        return Optional.ofNullable(getItems().get(slot));
    }

}