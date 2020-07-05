package team.unnamed.gui.item.type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PotionBuilder extends ItemBuilder {

    private List<PotionAttributes> potionAttributes = new ArrayList<>();

    public PotionBuilder(Material material) {
        super(material);
    }

    public PotionBuilder(Material material, int amount) {
        super(material, amount);
    }

    public PotionBuilder(Material material, int amount, short data) {
        super(material, amount, data);
    }

    public PotionBuilder name(String name) {
        super.name(name);

        return this;
    }

    public PotionBuilder lore(List<String> lore) {
        super.lore(lore);

        return this;
    }

    public PotionBuilder lore(LoreBuilder loreBuilder) {
        super.lore(loreBuilder);

        return this;
    }

    public PotionBuilder enchants(Map<Enchantment, Integer> enchants) {
        super.enchants(enchants);

        return this;
    }

    public PotionBuilder addEnchant(Enchantment enchantment, int level) {
        super.addEnchant(enchantment, level);

        return this;
    }

    public PotionBuilder flags(List<ItemFlag> flags) {
        super.flags(flags);

        return this;
    }

    public PotionBuilder addFlag(ItemFlag flag) {
        super.addFlag(flag);

        return this;
    }

    public PotionBuilder potionAttributes(List<PotionAttributes> potionAttributes) {
        this.potionAttributes = potionAttributes;

        return this;
    }

    public PotionBuilder addPotionAttribute(PotionAttributes potionAttributes) {
        this.potionAttributes.add(potionAttributes);

        return this;
    }

    public ItemStack buildPotion() {
        if (!material.name().contains("POTION")) {
            throw new IllegalArgumentException("This material can not be a potion!");
        }

        ItemStack potion = build();

        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        potionAttributes.forEach(potionAttribute -> potionMeta.addCustomEffect(potionAttribute.build(), false));

        potion.setItemMeta(potionMeta);

        return potion;
    }

}