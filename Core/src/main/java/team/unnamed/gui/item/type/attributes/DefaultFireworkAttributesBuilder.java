package team.unnamed.gui.item.type.attributes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public class DefaultFireworkAttributesBuilder implements FireworkAttributesBuilder {

    private boolean flicker = false;
    private boolean trail = false;

    private FireworkEffect.Type type = FireworkEffect.Type.STAR;
    private Color[] colors;
    private Color[] colorsFade;

    @Override
    public FireworkAttributesBuilder flicker(boolean flicker) {
        this.flicker = flicker;

        return this;
    }

    @Override
    public FireworkAttributesBuilder trail(boolean trail) {
        this.trail = trail;

        return this;
    }

    @Override
    public FireworkAttributesBuilder type(FireworkEffect.Type type) {
        this.type = type;

        return this;
    }

    @Override
    public FireworkAttributesBuilder colors(Color... colors) {
        this.colors = colors;

        return this;
    }

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