package team.unnamed.gui.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    protected String name = "Unnamed Item";
    protected final int amount;
    protected final short data;
    protected Material material;
    protected List<String> lore;
    protected Map<Enchantment, Integer> enchants;
    protected List<ItemFlag> flags;

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ItemBuilder(Material material, int amount, short data) {
        checkMaterial(material);

        this.material = material;
        this.amount = amount;
        this.data = data;

        this.lore = new ArrayList<>();
        this.flags = new ArrayList<>();
        this.enchants = new HashMap<>();
    }

    public ItemBuilder(Material material, int amount, short data, LoreBuilder builder) {
        checkMaterial(material);

        this.material = material;
        this.amount = amount;
        this.data = data;

        this.lore = builder.build();
        this.flags = new ArrayList<>();
        this.enchants = new HashMap<>();
    }

    public ItemBuilder name(String name) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);

        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;

        return this;
    }

    public ItemBuilder lore(LoreBuilder loreBuilder) {
        this.lore = loreBuilder.build();

        return this;
    }

    public ItemBuilder enchants(Map<Enchantment, Integer> enchants) {
        this.enchants = enchants;

        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, Integer level) {
        enchants.put(enchantment, level);

        return this;
    }

    public ItemBuilder flags(List<ItemFlag> flags) {
        this.flags = flags;

        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        this.flags.add(flag);

        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount, data);

        ItemMeta meta = item.getItemMeta();

        if (!enchants.isEmpty()) {
            enchants.forEach(((enchantment, level) -> meta.addEnchant(enchantment, level, true)));
        }

        meta.setDisplayName(name);
        meta.setLore(lore);

        if (!flags.isEmpty()) {
            flags.forEach(meta::addItemFlags);
        }

        item.setItemMeta(meta);

        return item;
    }

    private void checkMaterial(Material material) {
        if (material == null) {
            this.material = Material.STONE;
        }
    }

}