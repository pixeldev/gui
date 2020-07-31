package team.unnamed.gui.item.type;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import team.unnamed.gui.item.type.attributes.LeatherArmorColor;

public class LeatherArmorBuilder extends DefaultItemBuilder {

    private int red;
    private int green;
    private int blue;

    LeatherArmorBuilder(Material material) {
        super(material);
    }

    LeatherArmorBuilder rgbColor(LeatherArmorColor color) {
        this.red = color.getRed();
        this.blue = color.getBlue();
        this.green = color.getGreen();

        return this;
    }

    LeatherArmorBuilder rgbColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        return this;
    }

    @Override
    public ItemStack build() {
        if (!material.name().startsWith("LEATHER_")) {
            throw new IllegalArgumentException("This material can not be leather armor!");
        }

        ItemStack item = super.build();

        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) item.getItemMeta();

        leatherArmorMeta.setColor(Color.fromBGR(blue, green, red));

        item.setItemMeta(leatherArmorMeta);

        return item;
    }

}