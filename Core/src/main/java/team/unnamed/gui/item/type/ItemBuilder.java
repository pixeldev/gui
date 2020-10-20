package team.unnamed.gui.item.type;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * An utility class to make the creation of custom items easier
 */
public interface ItemBuilder {

    /**
     * Sets the name for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method will override the last value set
     *
     * @param name The new name for the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    ItemBuilder name(String name);

    /**
     * Sets the lore for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method will override the last value set
     *
     * @param lore The new lore for the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    default ItemBuilder lore(String... lore) {
        return lore(Lists.newArrayList(lore));
    }

    /**
     * Sets the lore for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method will override the last value set
     *
     * @param lore The new lore for the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    ItemBuilder lore(List<String> lore);

    /**
     * Sets a set of enchantments for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method will override the last value set
     *
     * @param enchantments The new enchantments map for the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    ItemBuilder enchants(Map<Enchantment, Integer> enchantments);

    /**
     * Adds a enchantment for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method with the same specified {@link Enchantment} will lead to overriding the last level for the enchantment
     *
     * @param enchantment The new {@link Enchantment} added to the {@linkplain ItemStack} being build
     * @param level The level of the {@link Enchantment} being added
     * @return The same mutable instance of ItemBuilder
     */
    ItemBuilder addEnchant(Enchantment enchantment, Integer level);

    /**
     * Sets a set of flags for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method will override the last value set
     *
     * @param flags The new flags list for the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    default ItemBuilder flags(ItemFlag flags) {
        return flags(Lists.newArrayList(flags));
    }

    /**
     * Sets a set of flags for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method will override the last value set
     *
     * @param flags The new flags list for the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    ItemBuilder flags(List<ItemFlag> flags);

    /**
     * Adds a flag for the {@linkplain ItemStack}
     * <p>
     * Multiple calls to this method with the same specified {@link ItemFlag} will lead to the Flag being added multiple times
     *
     * @param itemFlag The new {@link ItemFlag} added to the {@linkplain ItemStack} being build
     * @return The same mutable instance of ItemBuilder
     */
    ItemBuilder addFlag(ItemFlag itemFlag);

    /**
     * Builds the final {@link ItemStack} instance with the specified fields
     *
     * @return A non null {@link ItemStack} instance
     */
    ItemStack build();

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material}
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material) {
        return new DefaultItemBuilder(material);
    }

    /**
     * Creates a new instance of {@link ItemBuilder} with the specified {@link Material} and the specified amount
     *
     * @param material The material of the {@link ItemStack} to build
     * @param amount   The amount for the {@link ItemStack} to build
     * @return A new instance of {@link ItemBuilder}
     */
    static ItemBuilder newBuilder(Material material, int amount) {
        return new DefaultItemBuilder(material, amount);
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
        return new DefaultItemBuilder(material, amount, data);
    }

    /**
     * Creates a new instance of {@link LeatherArmorBuilder} with the specified {@link Material}
     * <p>
     * The specified {@link Material} should be any of the Leather pieces of armor like {@linkplain Material#LEATHER_CHESTPLATE}, the
     * builder won't raise an error when the {@link Material} isn't valid until you call the {@link LeatherArmorBuilder#build()} method
     *
     * @param material The material of the {@link ItemStack} to build
     * @return A new instance of {@link LeatherArmorBuilder}
     */
    static LeatherArmorBuilder newArmorBuilder(Material material) {
        return new LeatherArmorBuilder(material);
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