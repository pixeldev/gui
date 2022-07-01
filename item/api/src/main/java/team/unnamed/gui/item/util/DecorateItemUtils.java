package team.unnamed.gui.item.util;

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.item.ItemBuilder;

public final class DecorateItemUtils {

    private DecorateItemUtils() {
        throw new UnsupportedOperationException();
    }

    public static ItemBuilder newStainedPaneBuilder(DyeColor dyeColor) {
        return ItemBuilder.newDyeBuilder("STAINED_GLASS_PANE", dyeColor)
                .setName(" ");
    }

    public static ItemStack newStainedPane(DyeColor dyeColor) {
        return newStainedPaneBuilder(dyeColor).build();
    }

}
