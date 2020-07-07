package team.unnamed.gui.item.type;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;
import team.unnamed.gui.item.type.attributes.FireworkAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FireworkBuilder extends ItemBuilder {

    private int power = 3;
    private final List<FireworkAttributes> fireworkAttributes = new ArrayList<>();

    public FireworkBuilder(Material material) {
        super(material);
    }

    public FireworkBuilder name(String name) {
        super.name(name);

        return this;
    }

    public FireworkBuilder lore(List<String> lore) {
        super.lore(lore);

        return this;
    }

    public FireworkBuilder lore(LoreBuilder loreBuilder) {
        super.lore(loreBuilder);

        return this;
    }

    public FireworkBuilder enchants(Map<Enchantment, Integer> enchants) {
        super.enchants(enchants);

        return this;
    }

    public FireworkBuilder addEnchant(Enchantment enchantment, int level) {
        super.addEnchant(enchantment, level);

        return this;
    }

    public FireworkBuilder flags(List<ItemFlag> flags) {
        super.flags(flags);

        return this;
    }

    public FireworkBuilder addFlag(ItemFlag flag) {
        super.addFlag(flag);

        return this;
    }

    public FireworkBuilder addAttribute(FireworkAttributes fireworkAttribute) {
        fireworkAttributes.add(fireworkAttribute);

        return this;
    }

    public FireworkBuilder power(int power) {
        this.power = power;

        return this;
    }

    public ItemStack buildFirework() {
        if (material != Material.FIREWORK) {
            throw new IllegalArgumentException("This material can not be firework!");
        }

        ItemStack fireWork = build();

        FireworkMeta fireworkMeta = (FireworkMeta) fireWork.getItemMeta();

        fireworkAttributes.forEach(fireworkAttribute -> fireworkMeta.addEffect(
                FireworkEffect.builder()
                        .flicker(fireworkAttribute.isFlicker())
                        .trail(fireworkAttribute.isTrail())
                        .with(fireworkAttribute.getType())
                        .withColor(fireworkAttribute.getColor())
                        .withFade(fireworkAttribute.getColorFade())
                        .build()
        ));

        fireworkMeta.setPower(power);

        fireWork.setItemMeta(fireworkMeta);

        return fireWork;
    }

}