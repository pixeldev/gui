package team.unnamed.gui.item.type.attributes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public class FireworkAttributes {

    private boolean flicker = false;
    private boolean trail = false;
    private FireworkEffect.Type type = FireworkEffect.Type.CREEPER;
    private Color color = Color.GREEN;
    private Color colorFade = Color.RED;

    public FireworkAttributes flicker(boolean flicker) {
        this.flicker = flicker;

        return this;
    }

    public FireworkAttributes trail(boolean trail) {
        this.trail = trail;

        return this;
    }

    public FireworkAttributes type(FireworkEffect.Type type) {
        this.type = type;

        return this;
    }

    public FireworkAttributes color(Color color) {
        this.color = color;

        return this;
    }

    public FireworkAttributes colorFade(Color colorFade) {
        this.colorFade = colorFade;

        return this;
    }

    public boolean isFlicker() {
        return flicker;
    }

    public boolean isTrail() {
        return trail;
    }

    public FireworkEffect.Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorFade() {
        return colorFade;
    }

}