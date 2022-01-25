package team.unnamed.gui.core.gui.type;

import org.bukkit.entity.Player;
import team.unnamed.gui.core.opener.InventoryOpener;

public class SimpleGUIBuilder extends GUIBuilderLayout<SimpleGUIBuilder> {

    protected SimpleGUIBuilder(String title) {
        super(title);
    }

    protected SimpleGUIBuilder(String title, int rows) {
        super(title, rows);
    }

    @Override
    protected SimpleGUIBuilder back() {
        return this;
    }

    @Override
    public void open(Player player) {
        InventoryOpener.open(player, build());
    }

}