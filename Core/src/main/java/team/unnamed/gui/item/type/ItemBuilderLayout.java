package team.unnamed.gui.item.type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class ItemBuilderLayout<T extends ItemBuilder> implements ItemBuilder {

    protected final Material material;
    private final int amount;
    private final byte data;

    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<ItemFlag> flags = new ArrayList<>();

    ItemBuilderLayout(Material material, int amount, byte data) {
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T name(String name) {
        this.name = name;
        return back();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T lore(List<String> lore) {
        this.lore = lore;
        return back();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T enchants(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return back();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T addEnchant(Enchantment enchantment, Integer level) {
        enchantments.put(enchantment, level);
        return back();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T flags(List<ItemFlag> flags) {
        this.flags = flags;
        return back();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T addFlag(ItemFlag itemFlag) {
        flags.add(itemFlag);
        return back();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount, data);

        ItemMeta meta = item.getItemMeta();

        enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

        meta.setDisplayName(name);
        meta.setLore(lore);

        flags.forEach(meta::addItemFlags);

        item.setItemMeta(meta);

        return item;
    }

    protected abstract T back();

}