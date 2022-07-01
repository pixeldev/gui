package team.unnamed.gui.menu.plugin;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.menu.item.ItemClickable;
import team.unnamed.gui.menu.listener.InventoryClickListener;
import team.unnamed.gui.menu.listener.InventoryCloseListener;
import team.unnamed.gui.menu.listener.InventoryOpenListener;
import team.unnamed.gui.menu.type.MenuInventory;

import java.util.ArrayList;
import java.util.List;

import static team.unnamed.gui.item.util.DecorateItemUtils.newStainedPane;

public class MenuPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new InventoryOpenListener(), this);
        pluginManager.registerEvents(new InventoryCloseListener(this), this);

        getCommand("gui").setExecutor((sender, command, label, args) -> {
            Player player = (Player) sender;

            switch (args[0]) {
                case "default": {
                    player.openInventory(MenuInventory.newBuilder("Test")
                            .fillBorders(ItemClickable.onlyItem(newStainedPane(DyeColor.PINK)))
                            .addItem(ItemClickable.builder(22)
                                    .setItem(new ItemStack(Material.ENDER_PEARL))
                                    .setAction(inventory -> {
                                        player.sendMessage("Testing");
                                        player.closeInventory();
                                        return true;
                                    })
                                    .build())
                            .setOpenAction(inventory -> {
                                player.sendMessage("Opening...");
                                return false;
                            })
                            .setCloseAction(inventory -> {
                                player.sendMessage("Closing...");

                                return false;
                            })
                            .build());
                    break;
                }
                case "paginated": {
                    List<ItemStack> entities = new ArrayList<>();

                    for (int i = 0; i <= 90; i++) {
                        entities.add(ItemBuilder.newBuilder(Material.ENDER_PEARL)
                                .setName("Item #" + i)
                                .build()
                        );
                    }

                    ItemClickable decorationItem = ItemClickable.onlyItem(
                            newStainedPane(DyeColor.PINK)
                    );

                    player.openInventory(MenuInventory
                            .newPaginatedBuilder(ItemStack.class, "Paginated Test")
                            .setEntities(entities)
                            .setItemsPerRow(7)
                            .setEntityParser(ItemClickable::onlyItem)
                            .setSkippedSlots(10, 16, 28, 34, 37, 38, 42, 43)
                            .setBounds(10, 44)
                            .setItemIfNoPreviousPage(decorationItem)
                            .setItemIfNoNextPage(decorationItem)
                            .setNextPageItem(page -> ItemClickable.onlyItem(ItemBuilder.newBuilder(Material.ARROW)
                                    .setName("Next page - " + page)
                                    .build()))
                            .setPreviousPageItem(page -> ItemClickable.onlyItem(ItemBuilder.newBuilder(Material.ARROW)
                                    .setName("Previous page - " + page)
                                    .build()))
                            .setLayoutLines(
                                    "xxxxxxxxx",
                                    "xseeeeesx",
                                    "xeeeeeeex",
                                    "xseeeeesx",
                                    "xsseeessx",
                                    "xpxxxxxnx"
                            )
                            .setLayoutItem('s', ItemClickable.onlyItem(newStainedPane(DyeColor.WHITE)))
                            .setLayoutItem('x', decorationItem)
                            .build());
                    break;
                }
                default: break;
            }

            return true;
        });
    }

}
