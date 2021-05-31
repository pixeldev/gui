package team.unnamed.gui.core.item.type;

import org.bukkit.DyeColor;
import org.bukkit.Material;

public class DyeItemBuilder extends ItemBuilderLayout<DyeItemBuilder> {

  protected DyeItemBuilder(Material material) {
    this(material, 1, DyeColor.WHITE);
  }

  protected DyeItemBuilder(Material material, DyeColor dyeColor) {
    this(material, 1, dyeColor);
  }

  protected DyeItemBuilder(Material material, int amount, DyeColor dyeColor) {
    super(material, amount, dyeColor.getWoolData());
  }

  @Override
  protected DyeItemBuilder back() {
    return this;
  }

}