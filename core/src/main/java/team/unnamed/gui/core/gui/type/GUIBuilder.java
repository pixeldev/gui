package team.unnamed.gui.core.gui.type;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface GUIBuilder {

  GUIBuilder fillItem(ItemClickable item, int from, int to);

  GUIBuilder setItems(List<ItemClickable> items);

  GUIBuilder addItem(ItemClickable itemClickable);

  GUIBuilder openAction(Predicate<InventoryOpenEvent> openAction);

  GUIBuilder closeAction(Consumer<InventoryCloseEvent> closeAction);

  GUIBuilder toggleClick();

  Inventory build();

  static GUIBuilder builder(String title) {
    return new SimpleGUIBuilder(title);
  }

  static GUIBuilder builder(String title, int rows) {
    return new SimpleGUIBuilder(title, rows);
  }

  static StringLayoutGUIBuilder builderStringLayout(String title) {
    return new StringLayoutGUIBuilder(title);
  }

  static StringLayoutGUIBuilder builderStringLayout(String title, int rows) {
    return new StringLayoutGUIBuilder(title, rows);
  }

  static <E> PaginatedGUIBuilder<E> builderPaginated(Class<E> entityType, String title) {
    return new PaginatedGUIBuilder<>(title);
  }

  static <E> PaginatedGUIBuilder<E> builderPaginated(Class<E> entityType, String title, int rows) {
    return new PaginatedGUIBuilder<>(title, rows);
  }

}