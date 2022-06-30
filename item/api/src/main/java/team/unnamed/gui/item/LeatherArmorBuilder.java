package team.unnamed.gui.item;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import static team.unnamed.validate.Validate.isState;

public class LeatherArmorBuilder
        extends ItemBuilderLayout<LeatherArmorBuilder> {

    private int red;
    private int green;
    private int blue;

    protected LeatherArmorBuilder(Material material, int amount) {
        super(material, amount, (byte) 0);
    }

    public LeatherArmorBuilder fromLeatherColor(LeatherArmorColor armorColor) {
        return fromRgb(armorColor.getRed(), armorColor.getGreen(), armorColor.getBlue());
    }

    public LeatherArmorBuilder fromRgb(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    @Override
    public ItemStack build() {
        isState(material.name().startsWith("LEATHER_"),
                "Material must be leather armor.");

        ItemStack itemStack = super.build();
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        armorMeta.setColor(Color.fromRGB(this.red, this.green, this.blue));
        itemStack.setItemMeta(armorMeta);

        return itemStack;
    }

    @Override
    protected LeatherArmorBuilder back() {
        return this;
    }
}
