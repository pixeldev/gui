package team.unnamed.gui.menu.type;

import org.bukkit.inventory.Inventory;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.util.MenuUtil;
import team.unnamed.gui.menu.util.PaginatedMenuUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static team.unnamed.validate.Validate.isNotNull;

public class PaginatedMenuInventoryBuilder<E>
        extends StringLayoutMenuInventoryBuilder {

    private int entitySlotFrom;
    private int entitySlotTo;
    private int[] skippedSlots;
    private int itemsPerRow;
    private List<E> entities;
    private Function<E, ItemClickable> entityParser;
    private Function<Integer, ItemClickable> previousPageItem;
    private Function<Integer, ItemClickable> nextPageItem;
    private ItemClickable itemIfNoEntities;
    private ItemClickable itemIfNoPreviousPage;
    private ItemClickable itemIfNoNextPage;

    protected PaginatedMenuInventoryBuilder(String title) {
        super(title);
    }

    protected PaginatedMenuInventoryBuilder(String title, int rows) {
        super(title, rows);
    }

    public PaginatedMenuInventoryBuilder<E> bounds(int entitySlotFrom, int entitySlotTo) {
        this.entitySlotFrom = entitySlotFrom;
        this.entitySlotTo = entitySlotTo;
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> itemsPerRow(int itemsPerRow) {
        this.itemsPerRow = itemsPerRow;
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> skipSlots(int... skippedSlots) {
        this.skippedSlots = skippedSlots;
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> entities(Collection<E> entities) {
        this.entities = new ArrayList<>(entities);
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> entityParser(Function<E, ItemClickable> entityParser) {
        this.entityParser = entityParser;
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> nextPageItem(Function<Integer, ItemClickable> nextPageItem) {
        this.nextPageItem = nextPageItem;
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> previousPageItem(Function<Integer, ItemClickable> previousPageItem) {
        this.previousPageItem = previousPageItem;
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> itemIfNoEntities(ItemClickable itemIfNoEntities) {
        this.itemIfNoEntities = isNotNull(itemIfNoEntities, "Item if no entities cannot be null.");
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> itemIfNoPreviousPage(ItemClickable itemIfNoPreviousPage) {
        this.itemIfNoPreviousPage = isNotNull(itemIfNoPreviousPage,
                "Item if no previos page cannot be null.");
        return this;
    }

    public PaginatedMenuInventoryBuilder<E> itemIfNoNextPage(ItemClickable itemIfNoNextPage) {
        this.itemIfNoNextPage = isNotNull(itemIfNoNextPage,
                "Item if no next page cannot be null.");
        return this;
    }

    @Override
    public Inventory build() {
        isNotNull(entityParser, "Entity parser cannot be null.");
        isNotNull(entities, "Entities cannot be null.");
        isNotNull(nextPageItem, "Next page item cannot be null.");
        isNotNull(previousPageItem, "Previous page item cannot be null.");

        int nextIncrement = 9 - itemsPerRow;
        List<Integer> availableSlots = new ArrayList<>();
        int itemsPerRowCounter = 0;

        for (int i = entitySlotFrom; i < entitySlotTo; i++) {
            itemsPerRowCounter++;

            boolean isSkippedSlot = false;
            for (int skippedSlot : skippedSlots) {
                if (i == skippedSlot) {
                    isSkippedSlot = true;
                    break;
                }
            }

            if (!isSkippedSlot) {
                availableSlots.add(i);
            }

            if (itemsPerRowCounter == itemsPerRow) {
                itemsPerRowCounter = 0;
                i += nextIncrement;
            }
        }

        PaginatedMenuInventory<E> paginatedMenuInventory = new PaginatedMenuInventory<>(
                title, slots, items, openAction, closeAction, canIntroduceItems,
                entitySlotFrom, availableSlots.size(), availableSlots, entities, 1,
                layoutLines, layoutItems, entityParser,
                previousPageItem, nextPageItem, itemIfNoEntities,
                itemIfNoPreviousPage, itemIfNoNextPage
        );

        Inventory inventory = MenuUtil.parseToInventory(paginatedMenuInventory);
        return PaginatedMenuUtil.createPage(inventory, paginatedMenuInventory);
    }

    @Override
    protected PaginatedMenuInventoryBuilder<E> back() {
        return this;
    }

}
