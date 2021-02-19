package team.unnamed.gui.example;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.type.GUIBuilder;
import team.unnamed.gui.core.item.attribute.LeatherArmorColor;
import team.unnamed.gui.core.item.type.ItemBuilder;

@Command(names = "gui")
public class ExampleCommand implements CommandClass {

    @Command(names = "")
    public boolean runStringLayoutGuiCommand(@Sender Player player) {
        player.openInventory(GUIBuilder
                .builderStringLayout("Testing GUI")
                .setLayoutLines("axxxxxxxx")
                .setLayoutItem('a', ItemClickable
                        .builder()
                        .setItemStack(ItemBuilder
                                .newArmorBuilder(Material.LEATHER_BOOTS)
                                .fromLeatherColor(LeatherArmorColor.YELLOW)
                                .setName("Test Item")
                                .setLore("Just testing UnnamedGUI", "", "Only a test of ItemBuilder!")
                                .build())
                        .setAction(event -> {
                            player.sendMessage("Hi, just testing unnamed gui!");

                            return false;
                        })
                        .build()
                )
                .setLayoutItem('x', ItemClickable
                        .builder()
                        .setItemStack(new ItemStack(Material.STONE))
                        .setAction(event -> true)
                        .build()
                )
                .openAction(event -> {
                    player.sendMessage("Opening menu...");

                    return false;
                })
                .closeAction(event -> player.sendMessage("Closing menu..."))
                .build()
        );

        return true;
    }

    @Command(names = "")
    public boolean runGuiCommand(@Sender Player player) {
        player.openInventory(GUIBuilder
                .builder("Testing GUI")
                .addItem(ItemClickable
                        .builder(5)
                        .setItemStack(ItemBuilder
                                .newBuilder(Material.ENDER_PEARL)
                                .setName("Test Item")
                                .setLore("Just testing UnnamedGUI", "", "Only a test of ItemBuilder!")
                                .build())
                        .setAction(event -> {
                            player.sendMessage("Hi, just testing unnamed gui!");

                            return false;
                        })
                        .build()
                )
                .openAction(event -> {
                    player.sendMessage("Opening menu...");

                    return false;
                })
                .closeAction(event -> {
                    player.sendMessage("Closing menu...");
                })
                .build());

        return true;
    }

}