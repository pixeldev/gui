package team.unnamed.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import team.unnamed.gui.item.ItemClickable;
import team.unnamed.gui.menu.action.CloseMenuAction;
import team.unnamed.gui.menu.action.OpenMenuAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultMenuBuilder implements MenuBuilder {

    private final String title;
    private int rows;

    private List<ItemClickable> items = new ArrayList<>();

    private ItemClickable itemClickable;
    private OpenMenuAction openMenuAction;
    private CloseMenuAction closeMenuAction;

    private int from = -1;
    private int to = -1;

    private boolean cancelClick = true;

    protected DefaultMenuBuilder(String title) {
        this(title, 6);
    }

    protected DefaultMenuBuilder(String title, int rows) {
        this.title = title;
        this.rows = rows;
    }

    @Override
    public MenuBuilder setRows(int rows) {
        this.rows = rows;

        return this;
    }

    @Override
    public MenuBuilder fillItem(ItemClickable itemClickable, int from, int to) {
        this.itemClickable = itemClickable;
        this.from = from;
        this.to = to;

        return this;
    }

    @Override
    public MenuBuilder setItems(List<ItemClickable> items) {
        this.items = items;
        return this;
    }

    @Override
    public MenuBuilder addItem(ItemClickable itemClickable) {
        items.add(itemClickable);
        return this;
    }

    @Override
    public MenuBuilder openEvent(OpenMenuAction openMenuAction) {
        this.openMenuAction = openMenuAction;

        return this;
    }

    @Override
    public MenuBuilder closeEvent(CloseMenuAction closeMenuAction) {
        this.closeMenuAction = closeMenuAction;

        return this;
    }

    @Override
    public MenuBuilder cancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;

        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public List<ItemClickable> getItems() {
        return items;
    }

    @Override
    public Optional<OpenMenuAction> getOpenMenuAction() {
        return Optional.ofNullable(openMenuAction);
    }

    @Override
    public Optional<CloseMenuAction> getCloseMenuAction() {
        return Optional.ofNullable(closeMenuAction);
    }

    @Override
    public Optional<ItemClickable> getItemToFill() {
        return Optional.ofNullable(itemClickable);
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }

    @Override
    public boolean isCancelClick() {
        return cancelClick;
    }

    @Override
    public Inventory build() {
        GuiData guiData = new DefaultGuiData(title, rows, itemClickable, openMenuAction, closeMenuAction);
        Inventory inventory = new InventoryGui(Bukkit.createInventory(null, rows * 9, title), guiData);

        getItemToFill().ifPresent(itemClickable -> {
            if (from != -1 && to != -1) {
                for (int fill = from; fill <= to; fill++) {
                    inventory.setItem(fill, itemClickable.getItem());
                }
            }
        });

        items.forEach((itemClickable) ->
                inventory.setItem(
                        itemClickable.getSlot(),
                        itemClickable.getItem()));
        return inventory;
    }
}