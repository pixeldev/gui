package team.unnamed.gui.menu.v1_8_R3;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.unnamed.gui.menu.adapt.MenuInventoryWrapper;
import team.unnamed.gui.menu.type.MenuInventory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class MenuInventoryWrapperImpl
        implements MenuInventoryWrapper,
        InventoryHolder, Inventory {

    private final Inventory raw;
    private final MenuInventory menuInventory;

    public MenuInventoryWrapperImpl(
            InventoryHolder owner,
            MenuInventory menuInventory
    ) {
        this.menuInventory = menuInventory;

        raw = Bukkit.createInventory(
                owner,
                menuInventory.getSlots(),
                menuInventory.getTitle()
        );
    }

    @Override
    public int getSize() {
        return raw.getSize();
    }

    @Override
    public int getMaxStackSize() {
        return raw.getMaxStackSize();
    }

    @Override
    public void setMaxStackSize(int i) {
        raw.setMaxStackSize(i);
    }

    @Override
    public String getName() {
        return raw.getName();
    }

    @Override
    public ItemStack getItem(int i) {
        return raw.getItem(i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        raw.setItem(i, itemStack);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return raw.addItem(itemStacks);
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return raw.removeItem(itemStacks);
    }

    @Override
    public ItemStack[] getContents() {
        return raw.getContents();
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        raw.setContents(itemStacks);
    }

    @Override
    public boolean contains(int i) {
        return raw.contains(i);
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return raw.contains(material);
    }

    @Override
    public boolean contains(ItemStack itemStack) {
        return raw.contains(itemStack);
    }

    @Override
    public boolean contains(int i, int i1) {
        return raw.contains(i, i1);
    }

    @Override
    public boolean contains(Material material, int i) throws IllegalArgumentException {
        return raw.contains(material, i);
    }

    @Override
    public boolean contains(ItemStack itemStack, int i) {
        return raw.contains(itemStack, i);
    }

    @Override
    public boolean containsAtLeast(ItemStack itemStack, int i) {
        return raw.containsAtLeast(itemStack, i);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(int i) {
        return raw.all(i);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return raw.all(material);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
        return raw.all(itemStack);
    }

    @Override
    public int first(int i) {
        return raw.first(i);
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return raw.first(material);
    }

    @Override
    public int first(ItemStack itemStack) {
        return raw.first(itemStack);
    }

    @Override
    public int firstEmpty() {
        return raw.firstEmpty();
    }

    @Override
    public void remove(int i) {
        raw.remove(i);
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        raw.remove(material);
    }

    @Override
    public void remove(ItemStack itemStack) {
        raw.remove(itemStack);
    }

    @Override
    public void clear(int i) {
        raw.clear(i);
    }

    @Override
    public void clear() {
        raw.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return raw.getViewers();
    }

    @Override
    public String getTitle() {
        return raw.getTitle();
    }

    @Override
    public InventoryType getType() {
        return raw.getType();
    }

    @Override
    public InventoryHolder getHolder() {
        return this;
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return raw.iterator();
    }

    @Override
    public ListIterator<ItemStack> iterator(int i) {
        return raw.iterator(i);
    }

    @Override
    public Inventory getInventory() {
        return this;
    }

    @Override
    @NotNull
    public Inventory getRawInventory() {
        return this;
    }

    @Override
    @NotNull
    public MenuInventory getMenuInventory() {
        return menuInventory;
    }

}
