package team.unnamed.gui.core.gui.type;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import team.unnamed.gui.abstraction.item.ItemClickable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PaginatedGUIData<E> extends SimpleGUIData {

  private final String originalTitle;
  private final List<E> entities;
  private final List<ItemClickable> baseItems;
  private final Function<E, ItemClickable> itemParser;
  private final int currentPage;
  private final int from;
  private final int to;
  private int spaces;
  private int maxPages;
  private final int[] skippedSlotsInBounds;
  private final int itemsPerRow;
  private final List<Integer> availableSlots;
  private final ItemClickable previousPageItem;
  private final ItemClickable nextPageItem;

  public PaginatedGUIData(int slots,
                          List<ItemClickable> baseItems,
                          Predicate<InventoryOpenEvent> openAction,
                          Consumer<InventoryCloseEvent> closeAction,
                          boolean cancelClick,
                          String originalTitle, List<E> entities,
                          Function<E, ItemClickable> itemParser,
                          int currentPage, int from, int to,
                          List<Integer> availableSlots, int spaces,
                          int[] skippedSlotsInBounds, int itemsPerRow,
                          ItemClickable previousPageItem, ItemClickable nextPageItem) {

    super(originalTitle.replace("%page%", currentPage + ""),
        slots, new ArrayList<>(slots), openAction, closeAction, cancelClick
    );

    this.originalTitle = originalTitle;

    this.entities = entities;
    this.itemParser = itemParser;
    this.currentPage = currentPage;
    this.from = from;
    this.to = to;
    this.skippedSlotsInBounds = skippedSlotsInBounds;
    this.itemsPerRow = itemsPerRow;
    this.availableSlots = availableSlots;
    this.baseItems = baseItems;
    this.spaces = spaces;

    maxPages = (int) Math.ceil(entities.size() / (double) spaces);

    this.previousPageItem = previousPageItem;
    this.nextPageItem = nextPageItem;
  }

  public PaginatedGUIData(int slots,
                          List<ItemClickable> items,
                          Predicate<InventoryOpenEvent> openAction,
                          Consumer<InventoryCloseEvent> closeAction,
                          boolean cancelClick,
                          String originalTitle, List<E> entities,
                          Function<E, ItemClickable> itemParser,
                          int currentPage, int from, int to,
                          int[] skippedSlotsInBounds, int itemsPerRow,
                          ItemClickable previousPageItem, ItemClickable nextPageItem) {

    this(
        slots,
        new ArrayList<>(items),
        openAction, closeAction,
        cancelClick, originalTitle,
        entities, itemParser,
        currentPage, from, to,
        new ArrayList<>(), 0,
        skippedSlotsInBounds, itemsPerRow,
        previousPageItem, nextPageItem
    );


    if (itemsPerRow < 9) {
      int nextIncrement = 9 - itemsPerRow;
      int itemsPerRowCounter = 0;

      for (int i = from; i < to; i++) {
        itemsPerRowCounter++;

        if (!isSkippedSlot(i)) {
          availableSlots.add(i);
        }

        if (itemsPerRowCounter == itemsPerRow) {
          itemsPerRowCounter = 0;
          i += nextIncrement;
        }
      }

      this.spaces = availableSlots.size();
    } else {
      spaces = to - from;

      for (int i = from; i < to; i++) {
        availableSlots.add(i);
      }
    }

    maxPages = (int) Math.ceil(entities.size() / (double) spaces);
  }

  public List<E> getEntities() {
    return entities;
  }

  public Function<E, ItemClickable> getItemParser() {
    return itemParser;
  }

  public int getFrom() {
    return from;
  }

  public int getTo() {
    return to;
  }

  public int getSpaces() {
    return spaces;
  }

  public int getMaxPages() {
    return maxPages;
  }

  public ItemClickable getPreviousPageItem() {
    return previousPageItem;
  }

  public ItemClickable getNextPageItem() {
    return nextPageItem;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public List<Integer> getAvailableSlots() {
    return availableSlots;
  }

  public List<ItemClickable> getBaseItems() {
    return baseItems;
  }

  public int[] getSkippedSlotsInBounds() {
    return skippedSlotsInBounds;
  }

  public int getItemsPerRow() {
    return itemsPerRow;
  }

  public PaginatedGUIData<E> createNewDataFromPage(int newPage) {
    return new PaginatedGUIData<>(
        slots, baseItems,
        openAction, closeAction, cancelClick,
        originalTitle, entities, itemParser,
        newPage, from, to,
        availableSlots, spaces,
        skippedSlotsInBounds, itemsPerRow,
        previousPageItem, nextPageItem
    );
  }

  private boolean isSkippedSlot(int slotIndex) {
    for (int skippedSlot : skippedSlotsInBounds) {
      if (slotIndex == skippedSlot) {
        return true;
      }
    }

    return false;
  }

}