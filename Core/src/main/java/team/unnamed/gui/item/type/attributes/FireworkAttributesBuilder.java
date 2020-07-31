package team.unnamed.gui.item.type.attributes;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public interface FireworkAttributesBuilder {

    FireworkAttributesBuilder flicker(boolean flicker);

    FireworkAttributesBuilder trail(boolean trail);

    FireworkAttributesBuilder type(FireworkEffect.Type type);

    FireworkAttributesBuilder colors(Color... color);

    FireworkAttributesBuilder colorsFade(Color... colorFade);

    FireworkEffect build();

}