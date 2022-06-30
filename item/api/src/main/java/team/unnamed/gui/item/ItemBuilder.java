package team.unnamed.gui.item;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.util.DyeItemUtils;

import java.util.List;
import java.util.Map;

public interface ItemBuilder {

    ItemBuilder setName(String name);

    ItemBuilder setLore(List<String> lore);

    ItemBuilder setLore(String... lines);

    ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments);

    ItemBuilder addEnchant(Enchantment enchantment, int level);

    ItemBuilder setFlags(List<ItemFlag> flags);

    ItemBuilder addFlag(ItemFlag... flags);

    ItemBuilder grow();

    ItemBuilder setUnbreakable(boolean unbreakable);

    ItemStack build();

    static ItemBuilder newBuilder(Material material) {
        return newBuilder(material, 1);
    }

    static ItemBuilder newBuilder(Material material, int amount) {
        return newBuilder(material, amount, (byte) 0);
    }

    static ItemBuilder newBuilder(Material material, int amount, byte data) {
        return new DefaultItemBuilder(material, amount, data);
    }

    static SkullItemBuilder newSkullBuilder(int amount) {
        return new SkullItemBuilder(amount);
    }

    static ItemBuilder newDyeBuilder(String materialKey, DyeColor dyeColor) {
        return newDyeBuilder(materialKey, dyeColor, 1);
    }

    static ItemBuilder newDyeBuilder(String materialKey, DyeColor dyeColor, int amount) {
        return DyeItemUtils.createBuilder(materialKey, dyeColor, amount);
    }

}
