package team.unnamed.gui.item.type;

import org.bukkit.Material;

public class DefaultItemBuilder extends ItemBuilderLayout<DefaultItemBuilder> {

  DefaultItemBuilder(Material material) {
    this(material, 1);
  }

  DefaultItemBuilder(Material material, int amount) {
    this(material, amount, (byte) 0);
  }

  DefaultItemBuilder(Material material, int amount, byte data) {
    super(material, amount, data);
  }

  @Override
  protected DefaultItemBuilder back() {
    return this;
  }
}
