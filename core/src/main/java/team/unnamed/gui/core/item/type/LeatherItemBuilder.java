package team.unnamed.gui.core.item.type;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import team.unnamed.gui.core.item.LeatherArmorColor;

import static team.unnamed.validate.Validate.state;

public class LeatherItemBuilder extends ItemBuilderLayout<LeatherItemBuilder> {

  private int red;
  private int green;
  private int blue;

  protected LeatherItemBuilder(Material material, int amount) {
    super(material, amount, (short) 0);
  }

  public LeatherItemBuilder fromLeatherColor(LeatherArmorColor color) {
    return fromRgb(color.getRed(), color.getGreen(), color.getBlue());
  }

  public LeatherItemBuilder fromRgb(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;

    return this;
  }

  @Override
  public ItemStack build() {
    state(material.name().startsWith("LEATHER_"), "Material must be leather armor!");

    ItemStack itemStack = super.build();

    LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

    leatherArmorMeta.setColor(Color.fromRGB(red, green, blue));
    itemStack.setItemMeta(leatherArmorMeta);

    return itemStack;
  }

  @Override
  protected LeatherItemBuilder back() {
    return this;
  }

}