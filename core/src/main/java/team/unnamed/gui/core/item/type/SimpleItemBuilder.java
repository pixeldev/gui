package team.unnamed.gui.core.item.type;

import org.bukkit.Material;

public class SimpleItemBuilder extends ItemBuilderLayout<SimpleItemBuilder> {

    protected SimpleItemBuilder(Material material, int amount, short data) {
        super(material, amount, data);
    }

    @Override
    protected SimpleItemBuilder back() {
        return this;
    }

}