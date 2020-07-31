package team.unnamed.gui.item.type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public interface ItemBuilder {

    ItemBuilder name(String name);

    ItemBuilder lore(List<String> lore);

    ItemBuilder enchants(Map<Enchantment, Integer> enchantments);

    ItemBuilder addEnchant(Enchantment enchantment, Integer level);

    ItemBuilder flags(List<ItemFlag> flags);

    ItemBuilder addFlag(ItemFlag itemFlag);

    ItemStack build();

    static DefaultItemBuilder newBuilder(Material material) {
        return new DefaultItemBuilder(material);
    }

    static DefaultItemBuilder newBuilder(Material material, int amount) {
        return new DefaultItemBuilder(material, amount);
    }

    static DefaultItemBuilder newBuilder(Material material, int amount, byte data) {
        return new DefaultItemBuilder(material, amount, data);
    }

    static LeatherArmorBuilder newArmorBuilder(Material material) {
        return new LeatherArmorBuilder(material);
    }

}