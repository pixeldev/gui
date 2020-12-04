package team.unnamed.gui.core.item.type;

import org.bukkit.Material;
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

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material}
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material) {
        return new SimpleItemBuilder(material);
    }

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material} and the specified amount
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount   The amount for the {@link ItemStack} to build
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material, int amount) {
        return new SimpleItemBuilder(material, amount);
    }

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material}, the specified amount and byte data
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount   The amount for the {@link ItemStack} to build
     * @param data     Byte data for the item(passed through)
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material, int amount, byte data) {
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
        return new LeatherItemBuilder(material);
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
        return new FireworkBuilder(material);
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

    /**
     * Creates a new instance of {@link SkullBuilder} with the specified {@link Material} and the specified amount
     * <p>
     * The specified {@link Material} should be {@linkplain Material#SKULL_ITEM} or if you're
     * in 1.13+ the Material must be PLAYER_HEAD, the builder won't raise an error
     * when the {@link Material} isn't valid until you call the {@link SkullBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link SkullBuilder}
     */
    static SkullBuilder newSkullBuilder(Material material) {
        return new SkullBuilder(material);
    }

    /**
     * Creates a new instance of {@link SkullBuilder} with the specified {@link Material} and the specified amount
     * <p>
     * The specified {@link Material} should be {@linkplain Material#SKULL_ITEM} or if you're
     * in 1.13+ the Material must be PLAYER_HEAD, the builder won't raise an error
     * when the {@link Material} isn't valid until you call the {@link SkullBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount The amount of {@linkplain ItemStack} to build.
     * @return A new instance of {@link SkullBuilder}
     */
    static SkullBuilder newSkullBuilder(Material material, int amount) {
        return new SkullBuilder(material, amount);
    }

    /**
     * Creates a new instance of {@link SkullBuilder} with the specified {@link Material} and the specified amount
     * <p>
     * The specified {@link Material} should be {@linkplain Material#SKULL_ITEM} or if you're
     * in 1.13+ the Material must be PLAYER_HEAD, the builder won't raise an error
     * when the {@link Material} isn't valid until you call the {@link SkullBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount The amount of {@linkplain ItemStack} to build.
     * @param data The data of the {@linkplain ItemStack} to build.
     * @return A new instance of {@link SkullBuilder}
     */
    static SkullBuilder newSkullBuilder(Material material, int amount, byte data) {
        return new SkullBuilder(material, amount, data);
    }

}