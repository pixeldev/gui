package team.unnamed.gui.core.gui.type;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.PaginatedGUICreator;
import team.unnamed.gui.core.gui.factory.GUIFactory;
import team.unnamed.gui.core.opener.InventoryOpener;

import java.util.*;
import java.util.function.Function;

import static team.unnamed.validate.Validate.isNotNull;
import static team.unnamed.validate.Validate.isState;

public class PaginatedGUIBuilder<E> extends GUIBuilderLayout<PaginatedGUIBuilder<E>> {

    private final String originalTitle;
    private List<E> entities;
    private Function<E, ItemClickable> itemParser;
    private int from;
    private int to;
    private int[] skippedSlotsInBounds = {};
    private int itemsPerRow = 9;
    private Function<Integer, ItemClickable> previousPageItem;
    private Function<Integer, ItemClickable> nextPageItem;
    private final Set<Function<Integer, ItemClickable>> itemsReplacingWithPage
            = new HashSet<>();
    private ItemClickable itemIfNotEntities;
    private ItemClickable itemIfNotPreviousPage;

    protected PaginatedGUIBuilder(String title) {
        super(title);

        this.originalTitle = title;
    }

    protected PaginatedGUIBuilder(String title, int rows) {
        super(title, rows);

        this.originalTitle = title;
    }

    public PaginatedGUIBuilder<E> setEntities(Collection<E> entities) {
        isNotNull(entities, "Entities can't be null.");

        this.entities = new ArrayList<>(entities);

        return this;
    }

    public PaginatedGUIBuilder<E> setItemParser(Function<E, ItemClickable> itemParser) {
        isNotNull(itemParser, "Item parser can't be null.");

        this.itemParser = itemParser;

        return this;
    }

    public PaginatedGUIBuilder<E> setBounds(int from, int to) {
        isState(from >= 0, "From value must be major or equal than 0.");
        isState(to >= 0 && to <= 54, "From value must be major or equal than 0 and less or equal than 54.");
        isState(to > from, "To value must be major than from value.");

        this.from = from;
        this.to = to;

        return this;
    }

    public PaginatedGUIBuilder<E> skipSlotsInBounds(int... slots) {
        this.skippedSlotsInBounds = slots;

        return this;
    }

    public PaginatedGUIBuilder<E> setItemsPerRow(int amount) {
        isState(
                amount > 0 && amount <= 9,
                "Items per row cannot be equals than 0 or major than 9."
        );

        this.itemsPerRow = amount;

        return this;
    }

    public PaginatedGUIBuilder<E> addItemReplacingPage(Function<Integer, ItemClickable> item) {
        isNotNull(item, "Item can't be null.");

        itemsReplacingWithPage.add(item);

        return this;
    }

    public PaginatedGUIBuilder<E> setPreviousPageItem(Function<Integer, ItemClickable> previousPageItem) {
        isNotNull(previousPageItem, "Previous page item can't be null.");

        this.previousPageItem = previousPageItem;

        return this;
    }

    public PaginatedGUIBuilder<E> setNextPageItem(Function<Integer, ItemClickable> nextPageItem) {
        isNotNull(nextPageItem, "Next page item can't be null.");

        this.nextPageItem = nextPageItem;

        return this;
    }

    public PaginatedGUIBuilder<E> setItemIfNotEntities(ItemClickable itemIfNotEntities) {
        isNotNull(itemIfNotEntities, "Item if not entities can't be null.");

        this.itemIfNotEntities = itemIfNotEntities;

        return this;
    }

    public PaginatedGUIBuilder<E> setItemIfNotPreviousPage(ItemClickable itemIfNotPreviousPage) {
        isNotNull(itemIfNotPreviousPage, "Item if not previous page can't be null.");

        this.itemIfNotPreviousPage = itemIfNotPreviousPage;

        return this;
    }

    @Override
    public Inventory build() {
        isNotNull(itemParser, "Item parser can't be null.");
        isNotNull(entities, "Entities can't be null.");
        isNotNull(previousPageItem, "Previous page item can't be null.");
        isNotNull(nextPageItem, "Next page item can't be null.");

        PaginatedGUIData<E> guiData = new PaginatedGUIData<>(
                slots, new ArrayList<>(Arrays.asList(items)),
                openAction, closeAction, cancelClick,
                originalTitle, entities, itemParser,
                1, from, to,
                skippedSlotsInBounds, itemsPerRow,
                previousPageItem, nextPageItem,
                itemIfNotEntities, itemIfNotPreviousPage,
                itemsReplacingWithPage
        );

        Inventory inventory = GUIFactory.create(guiData);

        if (inventory == null) {
            return null;
        }

        return PaginatedGUICreator.createPage(inventory, guiData);
    }

    @Override
    public void open(Player player) {
        InventoryOpener.open(player, build());
    }

    @Override
    protected PaginatedGUIBuilder<E> back() {
        return this;
    }

}