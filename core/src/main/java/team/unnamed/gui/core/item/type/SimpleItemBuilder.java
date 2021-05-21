package team.unnamed.gui.core.item.type;

import org.bukkit.Material;

public class SimpleItemBuilder extends ItemBuilderLayout<SimpleItemBuilder> {

  protected SimpleItemBuilder(Material material) {
    this(material, 1);
  }

  protected SimpleItemBuilder(Material material, int amount) {
    this(material, amount, (byte) 0);
  }

  protected SimpleItemBuilder(Material material, int amount, byte data) {
    super(material, amount, data);
  }

  @Override
  protected SimpleItemBuilder back() {
    return this;
  }

}