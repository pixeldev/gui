package team.unnamed.gui.item.type;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;

import java.util.List;
import java.util.Map;

public class SkullBuilder extends ItemBuilder {

    private OfflinePlayer owner;

    public SkullBuilder(Material material) {
        super(material);
    }

    public SkullBuilder(Material material, int amount) {
        super(material, amount);
    }

    public SkullBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
    }

    public SkullBuilder name(String name) {
        super.name(name);

        return this;
    }

    public SkullBuilder lore(List<String> lore) {
        super.lore(lore);

        return this;
    }

    public SkullBuilder lore(LoreBuilder loreBuilder) {
        super.lore(loreBuilder);

        return this;
    }

    public SkullBuilder enchants(Map<Enchantment, Integer> enchants) {
        super.enchants(enchants);

        return this;
    }

    public SkullBuilder addEnchant(Enchantment enchantment, int level) {
        super.addEnchant(enchantment, level);

        return this;
    }

    public SkullBuilder flags(List<ItemFlag> flags) {
        super.flags(flags);

        return this;
    }

    public SkullBuilder addFlag(ItemFlag flag) {
        super.addFlag(flag);

        return this;
    }

    public SkullBuilder offlinePlayer(OfflinePlayer owner) {
        this.owner = owner;

        return this;
    }

    public ItemStack buildSkull() {
        if(!material.name().contains("SKULL_ITEM") || !material.name().contains("PLAYER_HEAD")) {
            throw new IllegalArgumentException("This material can not be a skull!");
        }

        ItemStack skull = build();

        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setOwner(owner.getName());

        skull.setItemMeta(skullMeta);

        return skull;
    }

}