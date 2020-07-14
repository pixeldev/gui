package team.unnamed.gui.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.button.SimpleButton;
import team.unnamed.gui.event.CloseMenuEvent;
import team.unnamed.gui.event.OpenMenuEvent;

import java.util.HashMap;
import java.util.Map;

public class MenuBuilder {

    private ItemStack itemForFill;
    private int fillFrom = -1;
    private int fillTo = -1;
    private boolean cancelFill = false;

    private final String title;
    private int rows;
    private Map<Integer, ItemStack> items;
    private Map<Integer, SimpleButton> buttons;

    private OpenMenuEvent openMenuEvent;
    private CloseMenuEvent closeMenuEvent;

    public MenuBuilder(String title) {
        this(title, 6);
    }

    public MenuBuilder(String title, int rows) {
        this.title = title;
        this.rows = rows;

        this.items = new HashMap<>();
        this.buttons = new HashMap<>();
    }

    public MenuBuilder rows(int rows) {
        this.rows = rows;

        return this;
    }

    public MenuBuilder fillItem(ItemStack itemForFill) {
        this.itemForFill = itemForFill;

        return this;
    }

    public MenuBuilder items(Map<Integer, ItemStack> items) {
        this.items = items;

        return this;
    }

    public MenuBuilder addItem(Integer slot, ItemStack item) {
        this.items.putIfAbsent(slot, item);

        return this;
    }

    public MenuBuilder buttons(Map<Integer, SimpleButton> buttons) {
        this.buttons = buttons;

        return this;
    }

    public MenuBuilder addButton(int slot, SimpleButton button) {
        this.buttons.put(slot, button);

        return this;
    }

    public MenuBuilder openEvent(OpenMenuEvent openMenuEvent) {
        this.openMenuEvent = openMenuEvent;

        return this;
    }

    public MenuBuilder closeEvent(CloseMenuEvent closeMenuEvent) {
        this.closeMenuEvent = closeMenuEvent;

        return this;
    }

    public MenuBuilder fillSlots(int from, int to) {
        fillFrom = from;
        fillTo = to;

        return this;
    }

    public MenuBuilder cancelFill(boolean cancelFill) {
        this.cancelFill = cancelFill;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Map<Integer, SimpleButton> getButtons() {
        return buttons;
    }

    public int getRows() {
        return rows;
    }

    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    public OpenMenuEvent getOpenMenuEvent() {
        return openMenuEvent;
    }

    public CloseMenuEvent getCloseMenuEvent() {
        return closeMenuEvent;
    }

    public ItemStack getItemForFill() {
        return itemForFill;
    }

    public int getFillFrom() {
        return fillFrom;
    }

    public int getFillTo() {
        return fillTo;
    }

    public boolean isCancelFill() {
        return cancelFill;
    }

    public Inventory build() {
        return new MenuHolder(this).getInventory();
    }

}