package team.unnamed.gui.example;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.abstraction.item.ItemClickable;
import team.unnamed.gui.core.gui.GUIBuilder;

@Command(names = "gui")
public class ExampleCommand implements CommandClass {

    @Command(names = "")
    public boolean runGuiCommand(@Sender Player player) {
        player.openInventory(GUIBuilder
                .builder("Testing GUI")
                .addItem(ItemClickable
                        .builder(5)
                        .setItemStack(new ItemStack(Material.ENDER_PEARL))
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