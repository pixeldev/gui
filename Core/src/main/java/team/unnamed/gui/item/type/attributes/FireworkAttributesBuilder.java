package team.unnamed.gui.item.type.attributes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

/**
 * An utility class to make the creation of {@link FireworkEffect} instances easy
 */
public interface FireworkAttributesBuilder {

    /**
     * @see FireworkEffect#hasFlicker()
     *
     * @param flicker If the created {@link FireworkEffect} should have flicker
     * @return The same instance of FireworkAttributesBuilder
     */
    FireworkAttributesBuilder flicker(boolean flicker);

    /**
     * @see FireworkEffect#hasTrail()
     *
     * @param trail If the created {@link FireworkEffect} should have trail
     * @return The same instance of FireworkAttributesBuilder
     */
    FireworkAttributesBuilder trail(boolean trail);

    /**
     * @see FireworkEffect#getType()
     *
     * @param type The {@link FireworkEffect.Type} for the {@link FireworkEffect} being build
     * @return The same instance of FireworkAttributesBuilder
     */
    FireworkAttributesBuilder type(FireworkEffect.Type type);

    /**
     * @see FireworkEffect#getColors()
     *
     * @param colors The primary {@link Color} list for the {@link FireworkEffect} being build
     * @return The same instance of FireworkAttributesBuilder
     */
    FireworkAttributesBuilder colors(Color... color);

    /**
     * @see FireworkEffect#getFadeColors()
     *
     * @param colors The fade {@link Color} list for the {@link FireworkEffect} being build
     * @return The same instance of FireworkAttributesBuilder
     */
    FireworkAttributesBuilder colorsFade(Color... colorFade);

    FireworkEffect build();

}