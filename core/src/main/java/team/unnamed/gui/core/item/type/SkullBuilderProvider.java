package team.unnamed.gui.core.item.type;

import org.bukkit.Material;

import static team.unnamed.gui.core.version.ServerVersionProvider.SERVER_VERSION_INT;

public class SkullBuilderProvider {

    private static final Material SKULL_MATERIAL;
    private static final short data;

    static {
        if (SERVER_VERSION_INT < 13) {
            SKULL_MATERIAL = Material.SKULL_ITEM;
            data = 3;
        } else {
            SKULL_MATERIAL = Material.valueOf("PLAYER_HEAD");
            data = 0;
        }
    }

    protected static SkullItemBuilder createBuilder(int amount) {
        return new SkullItemBuilder(SKULL_MATERIAL, amount, data);
    }

}