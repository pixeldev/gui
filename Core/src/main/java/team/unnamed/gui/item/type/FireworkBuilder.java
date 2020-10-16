package team.unnamed.gui.item.type;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

public class FireworkBuilder extends ItemBuilderLayout<FireworkBuilder> {

    private int power = 3;

    private final List<FireworkEffect> fireworkEffects = new ArrayList<>();

    FireworkBuilder(Material material) {
        this(material, 1);
    }

    FireworkBuilder(Material material, int amount) {
        super(material, amount, (byte) 0);
    }

    /**
     * Adds a {@link FireworkEffect} into the list of effects for the Firework being build
     *
     * @param fireworkEffect The {@linkplain FireworkEffect} to add into the firework
     * @return The same instance of FireworkBuilder
     */
    public FireworkBuilder addAttribute(FireworkEffect fireworkEffect) {
        fireworkEffects.add(fireworkEffect);

        return this;
    }

    /**
     * Sets the power for the Firework being build
     * @see FireworkMeta#setPower(int)
     *
     * @param power The power for the Firework from 0 to 128
     * @return The same instance of FireworkBuilder
     */
    public FireworkBuilder power(int power) {
        this.power = power;

        return this;
    }

    /**
     * Sets the {@link FireworkMeta} for the Firework item
     *
     * @throws IllegalArgumentException If the specified {@link Material} isn't a Firework
     *                                  <p>
     *                                  {@inheritDoc}
     */
    @Override
    public ItemStack build() {
        if (!material.name().startsWith("FIREWORK")) {
            throw new IllegalArgumentException("This material must be firework!");
        }

        ItemStack item = super.build();

        FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();

        fireworkEffects.forEach(fireworkMeta::addEffect);

        fireworkMeta.setPower(power);

        item.setItemMeta(fireworkMeta);

        return item;
    }

    @Override
    protected FireworkBuilder back() {
        return this;
    }

}