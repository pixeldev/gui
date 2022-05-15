package team.unnamed.gui.core.item.type;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.core.item.flag.ItemFlag;

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

    ItemBuilder setFromGUI(boolean value);

    default ItemBuilder setFlags(ItemFlag... flags) {
        return setFlags(Arrays.asList(flags));
    }

    ItemStack build();

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material}
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material) {
        return newBuilder(material, 1);
    }

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material} and the specified amount
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount   The amount for the {@link ItemStack} to build
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material, int amount) {
        return newBuilder(material, amount, (short) 0);
    }

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material}, the specified amount and byte data
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount   The amount for the {@link ItemStack} to build
     * @param data     Byte data for the item(passed through)
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material, int amount, short data) {
        return new SimpleItemBuilder(material, amount, data);
    }

    /**
     * Creates a new instance of {@link LeatherItemBuilder} with the specified {@link Material}
     * <p>
     * The specified {@link Material} should be any of the Leather pieces of armor like {@linkplain Material#LEATHER_CHESTPLATE}, the
     * builder won't raise an error when the {@link Material} isn't valid until you call the {@link LeatherItemBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link LeatherItemBuilder}
     */
    static LeatherItemBuilder newArmorBuilder(Material material) {
        return newArmorBuilder(material, 1);
    }

    static LeatherItemBuilder newArmorBuilder(Material material, int amount) {
        return new LeatherItemBuilder(material, amount);
    }

    /**
     * Creates a new instance of {@link FireworkBuilder} with the specified {@link Material}
     * <p>
     * The specified {@link Material} should be {@linkplain Material#FIREWORK}, the builder won't raise an error
     * when the {@link Material} isn't valid until you call the {@link FireworkBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link FireworkBuilder}
     */
    static FireworkBuilder newFireworkBuilder(Material material) {
        return newFireworkBuilder(material, 1);
    }

    /**
     * Creates a new instance of {@link FireworkBuilder} with the specified {@link Material} and the specified amount
     * <p>
     * The specified {@link Material} should be {@linkplain Material#FIREWORK}, the builder won't raise an error
     * when the {@link Material} isn't valid until you call the {@link FireworkBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount   The amount of {@link ItemStack} to build
     * @return A new instance of {@link FireworkBuilder}
     */
    static FireworkBuilder newFireworkBuilder(Material material, int amount) {
        return new FireworkBuilder(material, amount);
    }

    static SkullItemBuilder newSkullBuilder(int amount) {
        return SkullBuilderProvider.createBuilder(amount);
    }

    static ItemBuilder newDyeItemBuilder(String materialKey) {
        return newDyeItemBuilder(materialKey, 1, DyeColor.WHITE);
    }

    static ItemBuilder newDyeItemBuilder(String materialKey, DyeColor dyeColor) {
        return newDyeItemBuilder(materialKey, 1, dyeColor);
    }

    static ItemBuilder newDyeItemBuilder(String materialKey, int amount, DyeColor dyeColor) {
        return DyeBuilderProvider.createBuilder(materialKey, dyeColor, amount);
    }

}