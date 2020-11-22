package team.unnamed.gui.core.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface ItemBuilder {

    ItemBuilder setName(String name);

    ItemBuilder setLore(List<String> lore);

    default ItemBuilder setLore(String... lines) {
        return setLore(Arrays.asList(lines));
    }

    ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments);

    ItemBuilder addEnchant(Enchantment enchantment, int level);

    ItemBuilder setFlags(List<ItemFlag> flags);

    ItemBuilder addFlag(ItemFlag flag);

    default ItemBuilder setFlags(ItemFlag... flags) {
        return setFlags(Arrays.asList(flags));
    }

    ItemStack build();

}