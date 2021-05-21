package team.unnamed.gui.core;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.type.PaginatedGUIData;

import java.util.List;
import java.util.function.Function;

public class PaginatedGUICreator {

  public static <E> Inventory createPage(Inventory delegate, PaginatedGUIData<E> guiData) {
    int currentPage = guiData.getCurrentPage();
    List<E> entities = guiData.getEntities();
    Function<E, ItemClickable> itemParser = guiData.getItemParser();
    int spaces = guiData.getSpaces();

    List<ItemClickable> items = guiData.getItems();

    for (int i = guiData.getFrom(); i < guiData.getTo(); i++) {
      items.set(i, null);
    }

    int slotIndex = guiData.getFrom();

    int entityIndexStart = currentPage == 1 ? 0 : spaces * (currentPage - 1);
    int entityIndexEnd = spaces * currentPage;

    boolean hasNextPage = true;

    for (int i = entityIndexStart; i < entityIndexEnd; i++) {
      if (i >= entities.size()) {
        hasNextPage = false;

        break;
      }

      E entity = entities.get(i);
      ItemClickable itemClickable = itemParser.apply(entity);

      items.set(
          slotIndex,
          itemClickable.cloneInSlot(slotIndex)
      );

      slotIndex++;
    }

    ItemClickable nextPageItem = guiData.getNextPageItem();
    ItemClickable previousPageItem = guiData.getPreviousPageItem();

    if (currentPage == 1) {
      items.set(previousPageItem.getSlot(), null);
    } else {
      items.set(previousPageItem.getSlot(), previousPageItem);
    }

    if (!hasNextPage) {
      items.set(nextPageItem.getSlot(), null);
    } else {
      items.set(nextPageItem.getSlot(), nextPageItem);
    }

    for (ItemClickable itemClickable : items) {
      if (itemClickable == null) {
        continue;
      }

      delegate.setItem(itemClickable.getSlot(), itemClickable.getItemStack());
    }

    return delegate;
  }

}