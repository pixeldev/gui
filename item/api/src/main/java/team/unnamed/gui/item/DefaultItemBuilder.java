package team.unnamed.gui.item;

import org.bukkit.Material;

public class DefaultItemBuilder
        extends ItemBuilderLayout<DefaultItemBuilder> {

    protected DefaultItemBuilder(
            Material material, int amount,
            byte data
    ) {
        super(material, amount, data);
    }

    @Override
    protected DefaultItemBuilder back() {
        return this;
    }
}
