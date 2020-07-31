package team.unnamed.gui.item.type.attributes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

/**
 * An utility class to make the creation of {@link FireworkEffect} instances easy
 */
public class DefaultFireworkAttributesBuilder implements FireworkAttributesBuilder {

    private boolean flicker = false;
    private boolean trail = false;

    private FireworkEffect.Type type = FireworkEffect.Type.STAR;
    private Color[] colors;
    private Color[] colorsFade;

    /**
     * @see FireworkEffect#hasFlicker()
     *
     * @param flicker If the created {@link FireworkEffect} should have flicker
     * @return The same instance of FireworkAttributesBuilder
     */
    @Override
    public FireworkAttributesBuilder flicker(boolean flicker) {
        this.flicker = flicker;

        return this;
    }

    /**
     * @see FireworkEffect#hasTrail()
     *
     * @param trail If the created {@link FireworkEffect} should have trail
     * @return The same instance of FireworkAttributesBuilder
     */
    @Override
    public FireworkAttributesBuilder trail(boolean trail) {
        this.trail = trail;

        return this;
    }

    /**
     * @see FireworkEffect#getType()
     *
     * @param type The {@link FireworkEffect.Type} for the {@link FireworkEffect} being build
     * @return The same instance of FireworkAttributesBuilder
     */
    @Override
    public FireworkAttributesBuilder type(FireworkEffect.Type type) {
        this.type = type;

        return this;
    }

    /**
     * @see FireworkEffect#getColors()
     *
     * @param colors The primary {@link Color} list for the {@link FireworkEffect} being build
     * @return The same instance of FireworkAttributesBuilder
     */
    @Override
    public FireworkAttributesBuilder colors(Color... colors) {
        this.colors = colors;

        return this;
    }

    /**
     * @see FireworkEffect#getFadeColors()
     *
     * @param colors The fade {@link Color} list for the {@link FireworkEffect} being build
     * @return The same instance of FireworkAttributesBuilder
     */
    @Override
    public FireworkAttributesBuilder colorsFade(Color... colorsFade) {
        this.colorsFade = colorsFade;

        return this;
    }

    @Override
    public FireworkEffect build() {
        if (colors == null || colorsFade == null) {
            throw new NullPointerException("Colors can not be empty or null!");
        }

        return FireworkEffect.builder()
                .flicker(flicker)
                .trail(trail)
                .with(type)
                .withColor(colors)
                .withFade(colorsFade)
                .build();
    }
    
}