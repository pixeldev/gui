package team.unnamed.gui.item.type;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionAttributes {

    private final PotionEffectType potionEffectType;
    private int amplifier;
    private int duration;
    private boolean ambient;
    private boolean particles;

    public PotionAttributes(PotionEffectType potionEffectType) {
        this.potionEffectType = potionEffectType;

        this.amplifier = 0;
        this.duration = 1;
        this.ambient = true;
        this.particles = true;
    }

    public PotionAttributes amplifier(int amplifier) {
        this.amplifier = amplifier;

        return this;
    }

    public PotionAttributes duration(int duration) {
        this.duration = duration;

        return this;
    }

    public PotionAttributes ambient(boolean ambient) {
        this.ambient = ambient;

        return this;
    }

    public PotionAttributes particles(boolean particles) {
        this.particles = particles;

        return this;
    }

    public PotionEffect build() {
        return new PotionEffect(potionEffectType, duration, amplifier, ambient, particles);
    }

}