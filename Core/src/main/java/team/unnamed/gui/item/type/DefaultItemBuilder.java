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

public class DefaultItemBuilder implements ItemBuilder {

    protected final Material material;
    private final int amount;
    private final byte data;

    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<ItemFlag> flags = new ArrayList<>();

    DefaultItemBuilder(Material material) {
        this(material, 1);
    }

    DefaultItemBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    DefaultItemBuilder(Material material, int amount, byte data) {
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemBuilder name(String name) {
        this.name = name;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemBuilder enchants(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemBuilder addEnchant(Enchantment enchantment, Integer level) {
        enchantments.put(enchantment, level);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemBuilder flags(List<ItemFlag> flags) {
        this.flags = flags;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemBuilder addFlag(ItemFlag itemFlag) {
        flags.add(itemFlag);

        return this;
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

}