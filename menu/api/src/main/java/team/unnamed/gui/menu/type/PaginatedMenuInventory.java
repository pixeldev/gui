package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class PaginatedMenuInventory<E> extends DefaultMenuInventory {

    private final int entitySlotFrom;
    private final int availableEntitySlots;
    private final List<Integer> availableSlots;
    private final int maxPages;
    private final List<E> entities;
    private final int currentPage;
    private final List<String> layoutLines;
    private final Map<Character, ItemClickable> layoutItems;
    private final Function<E, ItemClickable> entityParser;
    private final Function<Integer, ItemClickable> previousPageItem;
    private final Function<Integer, ItemClickable> nextPageItem;
    private final ItemClickable itemIfNoEntities;
    private final ItemClickable itemIfNoPreviousPage;
    private final ItemClickable itemIfNoNextPage;

    protected PaginatedMenuInventory(
            String title, int slots,
            List<ItemClickable> items,
            Predicate<Inventory> openAction,
            Predicate<Inventory> closeAction,
            boolean canIntroduceItems,
            int entitySlotFrom, int availableEntitySlots,
            List<Integer> availableSlots, List<E> entities,
            int currentPage, List<String> layoutLines,
            Map<Character, ItemClickable> layoutItems,
            Function<E, ItemClickable> entityParser,
            Function<Integer, ItemClickable> previousPageItem,
            Function<Integer, ItemClickable> nextPageItem,
            ItemClickable itemIfNoEntities,
            ItemClickable itemIfNoPreviousPage,
            ItemClickable itemIfNoNextPage
    ) {
        super(title, slots, items, openAction, closeAction, canIntroduceItems);
        this.entitySlotFrom = entitySlotFrom;
        this.availableEntitySlots = availableEntitySlots;
        this.availableSlots = availableSlots;
        this.maxPages = (int) Math.ceil(entities.size() / (double) availableEntitySlots);
        this.entities = entities;
        this.currentPage = currentPage;
        this.layoutLines = layoutLines;
        this.layoutItems = layoutItems;
        this.entityParser = entityParser;
        this.previousPageItem = previousPageItem;
        this.nextPageItem = nextPageItem;
        this.itemIfNoEntities = itemIfNoEntities;
        this.itemIfNoPreviousPage = itemIfNoPreviousPage;
        this.itemIfNoNextPage = itemIfNoNextPage;
    }

    public int getEntitySlotFrom() {
        return entitySlotFrom;
    }

    public List<Integer> getAvailableSlots() {
        return availableSlots;
    }

    public int getAvailableEntitySlots() {
        return availableEntitySlots;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<E> getEntities() {
        return entities;
    }

    public List<String> getLayoutLines() {
        return layoutLines;
    }

    public Map<Character, ItemClickable> getLayoutItems() {
        return layoutItems;
    }

    public Function<E, ItemClickable> getEntityParser() {
        return entityParser;
    }

    public Function<Integer, ItemClickable> getPreviousPageItem() {
        return previousPageItem;
    }

    public Function<Integer, ItemClickable> getNextPageItem() {
        return nextPageItem;
    }

    public ItemClickable getItemIfNoEntities() {
        return itemIfNoEntities;
    }

    public ItemClickable getItemIfNoPreviousPage() {
        return itemIfNoPreviousPage;
    }

    public ItemClickable getItemIfNoNextPage() {
        return itemIfNoNextPage;
    }

    public PaginatedMenuInventory<E> clone(int page) {
        return new PaginatedMenuInventory<>(
                title, slots, items, openAction, closeAction, canIntroduceItems,
                entitySlotFrom, availableEntitySlots, availableSlots,
                entities, page, layoutLines, layoutItems, entityParser,
                previousPageItem, nextPageItem, itemIfNoEntities,
                itemIfNoPreviousPage, itemIfNoNextPage
        );
    }

}
