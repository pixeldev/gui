package team.unnamed.gui.core.gui.type;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import team.unnamed.gui.abstraction.item.ItemClickable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PaginatedGUIData<E> extends SimpleGUIData {

  private final String originalTitle;
  private final List<E> entities;
  private final Function<E, ItemClickable> itemParser;
  private final int currentPage;
  private final int from;
  private final int to;
  private final int spaces;
  private final int maxPages;
  private final ItemClickable previousPageItem;
  private final ItemClickable nextPageItem;

  public PaginatedGUIData(int slots,
                          List<ItemClickable> items,
                          Predicate<InventoryOpenEvent> openAction,
                          Consumer<InventoryCloseEvent> closeAction,
                          boolean cancelClick,
                          String originalTitle, List<E> entities,
                          Function<E, ItemClickable> itemParser,
                          int currentPage, int from, int to,
                          ItemClickable previousPageItem, ItemClickable nextPageItem) {

    super(originalTitle.replace("%page%", currentPage + ""),
        slots, items, openAction, closeAction, cancelClick
    );

    this.originalTitle = originalTitle;

    this.entities = entities;
    this.itemParser = itemParser;
    this.currentPage = currentPage;
    this.from = from;
    this.to = to;

    spaces = to - from;
    maxPages = (int) Math.ceil(entities.size() / (double) spaces);

    this.previousPageItem = previousPageItem;
    this.nextPageItem = nextPageItem;
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

  public PaginatedGUIData<E> createNewDataFromPage(int newPage) {
    return new PaginatedGUIData<>(
        slots, items, openAction, closeAction, cancelClick,
        originalTitle, entities, itemParser,
        newPage, from, to,
        previousPageItem, nextPageItem
    );
  }

}