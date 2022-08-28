package team.unnamed.gui.item;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.util.DyeItemUtils;

import java.util.List;
import java.util.Map;

public interface ItemBuilder {

    ItemBuilder name(String name);

    ItemBuilder lore(List<String> lore);

    ItemBuilder lore(String... lines);

    ItemBuilder enchantments(Map<Enchantment, Integer> enchantments);

    ItemBuilder enchant(Enchantment enchantment, int level);

    ItemBuilder flags(List<ItemFlag> flags);

    ItemBuilder flag(ItemFlag... flags);

    ItemBuilder grow();

    ItemBuilder unbreakable(boolean unbreakable);

    ItemStack build();

    static ItemBuilder builder(Material material) {
        return builder(material, 1);
    }

    static ItemBuilder builder(Material material, int amount) {
        return builder(material, amount, (byte) 0);
    }

    static ItemBuilder builder(Material material, int amount, byte data) {
        return new DefaultItemBuilder(material, amount, data);
    }

    static ItemBuilder dyeBuilder(String materialKey, DyeColor dyeColor) {
        return dyeBuilder(materialKey, dyeColor, 1);
    }

    static ItemBuilder dyeBuilder(String materialKey, DyeColor dyeColor, int amount) {
        return DyeItemUtils.createBuilder(materialKey, dyeColor, amount);
    }

}
