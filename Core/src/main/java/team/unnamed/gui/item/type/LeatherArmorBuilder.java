package team.unnamed.gui.item.type;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import team.unnamed.gui.item.type.attributes.LeatherArmorColor;

public class LeatherArmorBuilder extends ItemBuilderLayout<LeatherArmorBuilder> {

    private int red;
    private int green;
    private int blue;

    LeatherArmorBuilder(Material material) {
        super(material, 1, (byte) 0);
    }

    /**
     * Sets the RGB color of the Leather Armor using {@link LeatherArmorColor} values
     *
     * @param color A value of the enum {@link LeatherArmorColor}
     * @return The same {@link LeatherArmorBuilder} instance
     */
    public LeatherArmorBuilder rgbColor(LeatherArmorColor color) {
        this.red = color.getRed();
        this.blue = color.getBlue();
        this.green = color.getGreen();

        return this;
    }

    /**
     * Sets the RGB color using raw values for the three colors to form a final color
     *
     * @param red   The value of the Red color
     * @param green The value of the Green Color
     * @param blue  The value of the Blue color
     * @return The same {@link LeatherArmorBuilder} instance
     */
    public LeatherArmorBuilder rgbColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        return this;
    }

    /**
     * Also sets the color for the Leather armor piece
     *
     * @throws IllegalArgumentException If the specified {@link Material} isn't a piece of Leather armor
     *                                  <p>
     *                                  {@inheritDoc}
     */
    @Override
    public ItemStack build() {
        if (!material.name().startsWith("LEATHER_")) {
            throw new IllegalArgumentException("This material must be leather armor!");
        }

        ItemStack item = super.build();

        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) item.getItemMeta();

        leatherArmorMeta.setColor(Color.fromBGR(blue, green, red));

        item.setItemMeta(leatherArmorMeta);
        return item;
    }

    @Override
    protected LeatherArmorBuilder back() {
        return this;
    }

}