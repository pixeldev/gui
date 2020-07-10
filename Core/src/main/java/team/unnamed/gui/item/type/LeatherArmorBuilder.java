package team.unnamed.gui.item.type;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;
import team.unnamed.gui.item.type.attributes.LeatherColor;

import java.util.List;
import java.util.Map;

public class LeatherArmorBuilder extends ItemBuilder {

    private int red;
    private int green;
    private int blue;

    public LeatherArmorBuilder(Material material) {
        super(material);
    }

    public LeatherArmorBuilder name(String name) {
        super.name(name);

        return this;
    }

    public LeatherArmorBuilder lore(List<String> lore) {
        super.lore(lore);

        return this;
    }

    public LeatherArmorBuilder lore(LoreBuilder loreBuilder) {
        super.lore(loreBuilder);

        return this;
    }

    public LeatherArmorBuilder enchants(Map<Enchantment, Integer> enchants) {
        super.enchants(enchants);

        return this;
    }

    public LeatherArmorBuilder addEnchant(Enchantment enchantment, int level) {
        super.addEnchant(enchantment, level);

        return this;
    }

    public LeatherArmorBuilder flags(List<ItemFlag> flags) {
        super.flags(flags);

        return this;
    }

    public LeatherArmorBuilder addFlag(ItemFlag flag) {
        super.addFlag(flag);

        return this;
    }

    public LeatherArmorBuilder rgbColor(LeatherColor color) {
        this.red = color.getRed();
        this.blue = color.getBlue();
        this.green = color.getGreen();

        return this;
    }

    public LeatherArmorBuilder rgbColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        return this;
    }

    public ItemStack buildArmor() {
        if (!material.name().startsWith("LEATHER_")) {
            throw new IllegalArgumentException("This material can not be leather armor!");
        }

        ItemStack armor = build();

        LeatherArmorMeta armorMeta = (LeatherArmorMeta) armor.getItemMeta();

        armorMeta.setColor(Color.fromBGR(blue, green, red));

        armor.setItemMeta(armorMeta);

        return armor;
    }

}