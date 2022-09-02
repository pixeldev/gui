package team.unnamed.gui.menu.util;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.item.action.ItemClickableAction;
import team.unnamed.gui.menu.type.PaginatedMenuInventory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class PaginatedMenuUtil {

    private PaginatedMenuUtil() {
        throw new UnsupportedOperationException();
    }

    public static <E> Inventory createPage(
            Inventory delegate,
            PaginatedMenuInventory<E> menuInventory
    ) {
        int currentPage = menuInventory.getCurrentPage();
        List<E> entities = menuInventory.getEntities();
        Map<Character, ItemClickable> layoutItems = menuInventory.getLayoutItems();
        Function<E, ItemClickable> entityParser = menuInventory.getEntityParser();
        int entitiesSize = entities.size();

        List<Integer> availableSlots = menuInventory.getAvailableSlots();
        int availableEntitySlots = menuInventory.getAvailableEntitySlots();
        int entityIndex = currentPage == 1 ? 0 : availableEntitySlots * (currentPage - 1);
        int currentSlot = 0;
        int entitySlotIndex = 0;

        ItemClickable itemIfNoEntities = menuInventory.getItemIfNoEntities();

        for (String layoutLine : menuInventory.getLayoutLines()) {
            for (char c : layoutLine.toCharArray()) {
                ItemClickable itemClickable = null;

                switch (c) {
                    case 'e': {
                        if (entityIndex >= entitiesSize) {
                            if (itemIfNoEntities != null)
                                itemClickable = itemIfNoEntities.clone(availableSlots.get(entitySlotIndex++));
                            break;
                        }

                        E entity = entities.get(entityIndex++);
                        itemClickable = entityParser.apply(entity).clone(availableSlots.get(entitySlotIndex++));
                        break;
                    }
                    case 'n': {
                        itemClickable = getInteractPageItem(
                                currentPage < menuInventory.getMaxPages(),
                                currentPage, currentPage + 1, currentSlot,
                                menuInventory, menuInventory.getNextPageItem(),
                                menuInventory.getItemIfNoNextPage()
                        );
                        break;
                    }
                    case 'p': {
                        itemClickable = getInteractPageItem(
                                currentPage > 1,
                                currentPage, currentPage - 1, currentSlot,
                                menuInventory, menuInventory.getPreviousPageItem(),
                                menuInventory.getItemIfNoPreviousPage()
                        );
                        break;
                    }
                    default: {
                        ItemClickable layoutItem = layoutItems.get(c);

                        if (layoutItem != null) {
                            itemClickable = layoutItem.clone(currentSlot);
                        }
                        break;
                    }
                }

                if (itemClickable != null) {
                    delegate.setItem(itemClickable.getSlot(), itemClickable.getItemStack());
                    menuInventory.setItem(itemClickable);
                }

                currentSlot++;
            }
        }

        return delegate;
    }

    private static ItemClickable getInteractPageItem(
            boolean expression, int currentPage, int newPage,
            int currentSlot,
            PaginatedMenuInventory<?> menuInventory,
            Function<Integer, ItemClickable> pageItem,
            ItemClickable orElseItem
    ) {
        ItemClickable itemClickable = null;
        if (expression) {
            itemClickable = pageItem.apply(currentPage)
                    .clone(currentSlot)
                    .clone(ItemClickableAction.single(event -> {
                        menuInventory.clearItems();
                        Inventory inventory = event.getClickedInventory();
                        inventory.clear();
                        createPage(inventory, menuInventory.clone(newPage));
                        return true;
                    }));
        } else {
            if (orElseItem != null) {
                itemClickable = orElseItem.clone(currentSlot);
            }
        }

        return itemClickable;
    }

}
