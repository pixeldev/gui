package team.unnamed.gui.core.item.type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.unnamed.gui.core.item.ItemBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.unnamed.validate.Validate.*;

abstract class ItemBuilderLayout<T extends ItemBuilder> implements ItemBuilder {

    protected final Material material;
    private final int amount;
    private final byte data;

    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<ItemFlag> flags = new ArrayList<>();

    protected ItemBuilderLayout(Material material, int amount, byte data) {
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    @Override
    public T setName(String name) {
        this.name = notNull(name, "Item name can't be null.");

        return back();
    }

    @Override
    public T setLore(List<String> lore) {
        this.lore = notNull(lore, "Item lore can't be null.");

        return back();
    }

    @Override
    public T setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = notNull(enchantments, "Item enchantments can't be null.");

        return back();
    }

    @Override
    public T addEnchant(Enchantment enchantment, int level) {
        notNull(enchantment, "Enchantment to add can't be null.");
        state(level >= 0, "Enchantment level must be higher or equals than 0.");

        enchantments.put(enchantment, level);

        return back();
    }

    @Override
    public T setFlags(List<ItemFlag> flags) {
        this.flags = notNull(flags, "Item flags can't be null.");

        return back();
    }

    @Override
    public T addFlag(ItemFlag flag) {
        notNull(flag, "Flag can't be null");

        flags.add(flag);

        return back();
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount, data);

        ItemMeta meta = itemStack.getItemMeta();

        enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

        meta.setDisplayName(name);
        meta.setLore(lore);

        flags.forEach(meta::addItemFlags);

        itemStack.setItemMeta(meta);

        return null;
    }

    protected abstract T back();

}