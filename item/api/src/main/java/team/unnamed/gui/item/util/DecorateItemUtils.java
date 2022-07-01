package team.unnamed.gui.item.util;

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.ItemBuilder;

public final class DecorateItemUtils {

    private DecorateItemUtils() {
        throw new UnsupportedOperationException();
    }

    public static ItemBuilder stainedPaneBuilder(DyeColor dyeColor) {
        return ItemBuilder.dyeBuilder("STAINED_GLASS_PANE", dyeColor)
                .name(" ");
    }

    public static ItemStack stainedPane(DyeColor dyeColor) {
        return stainedPaneBuilder(dyeColor).build();
    }

}
