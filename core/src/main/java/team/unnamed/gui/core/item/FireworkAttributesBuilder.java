package team.unnamed.gui.core.item;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

import static team.unnamed.validate.Validate.notNull;

public class FireworkAttributesBuilder {

    private boolean flicker;
    private boolean trail;

    private FireworkEffect.Type fireworkType = FireworkEffect.Type.STAR;
    private Color[] colors;
    private Color[] fadeColors;

    private FireworkAttributesBuilder() {
        throw new UnsupportedOperationException("This class can't be initialized.");
    }

    public FireworkAttributesBuilder toggleFlicker() {
        flicker = !flicker;

        return this;
    }

    public FireworkAttributesBuilder toggleTrail() {
        trail = !trail;

        return this;
    }

    public FireworkAttributesBuilder setType(FireworkEffect.Type fireworkType) {
        this.fireworkType = notNull(fireworkType, "Firework type can't be null.");

        return this;
    }

    public FireworkAttributesBuilder setColors(Color... colors) {
        this.colors = notNull(colors, "Colors can't be null.");

        return this;
    }

    public FireworkAttributesBuilder setFadeColors(Color... fadeColors) {
        this.fadeColors = notNull(fadeColors, "Fade colors can't be null.");

        return this;
    }

    public FireworkEffect build() {
        return FireworkEffect.builder()
                .flicker(flicker)
                .trail(trail)
                .with(fireworkType)
                .withColor(colors)
                .withFade(fadeColors)
                .build();
    }

    public static FireworkAttributesBuilder builder() {
        return new FireworkAttributesBuilder();
    }

}