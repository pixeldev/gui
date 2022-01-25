package team.unnamed.gui.core;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.type.PaginatedGUIData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PaginatedGUICreator {

    public static <E> Inventory createPage(Inventory delegate, PaginatedGUIData<E> guiData) {
        int currentPage = guiData.getCurrentPage();
        List<E> entities = guiData.getEntities();

        List<ItemClickable> items = guiData.getItems();
        List<ItemClickable> copyItems = new ArrayList<>(guiData.getBaseItems());

        int entitiesSize = entities.size();
        if (entitiesSize == 0) {
            ItemClickable itemIfNotEntities = guiData.getItemIfNotEntities();

            if (itemIfNotEntities != null) {
                copyItems.set(itemIfNotEntities.getSlot(), itemIfNotEntities);
            }
        } else {
            Function<E, ItemClickable> itemParser = guiData.getItemParser();
            int spaces = guiData.getSpaces();

            List<Integer> availableSlots = guiData.getAvailableSlots();

            int entityIndexStart = currentPage == 1 ? 0 : spaces * (currentPage - 1);
            int entityIndexEnd = spaces * currentPage;
            int slotIndex = 0;

            for (int i = entityIndexStart; i < entityIndexEnd; i++) {
                if (i >= entitiesSize) {
                    break;
                }

                E entity = entities.get(i);
                ItemClickable itemClickable = itemParser.apply(entity);

                if (itemClickable == null) {
                    continue;
                }

                int slot = availableSlots.get(slotIndex);

                copyItems.set(
                        slot,
                        itemClickable.cloneInSlot(slot)
                );

                slotIndex++;
            }
        }

        if (currentPage > 1) {
            ItemClickable previousPageItem = guiData.getPreviousPageItem().apply(currentPage - 1);

            copyItems.set(previousPageItem.getSlot(), previousPageItem);
        } else {
            ItemClickable itemIfNotPreviousPage = guiData.getItemIfNotPreviousPage();

            if (itemIfNotPreviousPage != null) {
                copyItems.set(itemIfNotPreviousPage.getSlot(), itemIfNotPreviousPage);
            }
        }

        if (currentPage < guiData.getMaxPages()) {
            ItemClickable nextPageItem = guiData.getNextPageItem().apply(currentPage + 1);
            copyItems.set(nextPageItem.getSlot(), nextPageItem);
        }

        for (Function<Integer, ItemClickable> itemReplacingPage :
                guiData.getItemsReplacingWithPage()
        ) {
            ItemClickable itemClickable = itemReplacingPage.apply(currentPage);
            copyItems.set(itemClickable.getSlot(), itemClickable);
        }

        for (ItemClickable itemClickable : copyItems) {
            items.add(itemClickable);

            if (itemClickable == null) {
                continue;
            }

            delegate.setItem(
                    itemClickable.getSlot(),
                    itemClickable.getItemStack()
            );
        }

        return delegate;
    }

}
