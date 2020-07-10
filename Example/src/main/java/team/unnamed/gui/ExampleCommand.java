package team.unnamed.gui;

import me.fixeddev.ebcm.parametric.CommandClass;
import me.fixeddev.ebcm.parametric.annotation.ACommand;
import me.fixeddev.ebcm.parametric.annotation.Injected;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import team.unnamed.gui.button.SimpleButton;
import team.unnamed.gui.item.ItemBuilder;
import team.unnamed.gui.item.LoreBuilder;
import team.unnamed.gui.item.type.FireworkBuilder;
import team.unnamed.gui.item.type.LeatherArmorBuilder;
import team.unnamed.gui.item.type.LeatherColor;
import team.unnamed.gui.item.type.attributes.FireworkAttributes;
import team.unnamed.gui.item.type.attributes.PotionAttributes;
import team.unnamed.gui.item.type.PotionBuilder;
import team.unnamed.gui.menu.MenuBuilder;

@ACommand(names = "menu")
public class ExampleCommand implements CommandClass {

    @ACommand(names = "item")
    public boolean itemCommand(@Injected(true) CommandSender sender) {
        Player player = (Player) sender;

        ItemStack itemStack = new ItemBuilder(Material.DIAMOND_SWORD)
                .addEnchant(Enchantment.DURABILITY, 3)
                .addFlag(ItemFlag.HIDE_ENCHANTS)
                .name("Test")
                .lore(
                        new LoreBuilder()
                                .addLine("Simple test %player%")
                                .replace("%player%", player.getName())
                ).build();

        player.getInventory().addItem(itemStack);

        return true;
    }

    @ACommand(names = "armor")
    public boolean armorCommand(@Injected(true) CommandSender sender) {
        Player player = (Player) sender;

        ItemStack chestPlate = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE)
                .rgbColor(LeatherColor.CYAN)
                .name("&bArmor test")
                .lore(
                        new LoreBuilder()
                                .addLine("&7Simple test for leather armor!")
                                .addLine("")
                                .addLine("&bEnjoy!")
                                .colorize()
                )
                .addEnchant(Enchantment.DURABILITY, 3)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                .addFlag(ItemFlag.HIDE_ATTRIBUTES)
                .buildArmor();

        player.getEquipment().setChestplate(chestPlate);

        return true;
    }

    @ACommand(names = "potion")
    public boolean potionCommand(@Injected(true) CommandSender sender) {
        Player player = (Player) sender;

        ItemStack potion = new PotionBuilder(Material.POTION)
                .addPotionAttribute(
                        new PotionAttributes(PotionEffectType.POISON)
                                .duration(1000)
                                .amplifier(2)
                )
                .name("&bPotion test")
                .lore(
                        new LoreBuilder()
                                .addLine("Simple test for potion builder!")
                                .addLine("")
                                .addLine("&bEnjoy!")
                                .colorize()
                )
                .buildPotion();

        player.getInventory().addItem(potion);

        return true;
    }

    @ACommand(names = "")
    public boolean menuCommand(@Injected(true) CommandSender sender) {
        Player player = (Player) sender;

        MenuBuilder menuBuilder = new MenuBuilder("MenuTest", 5)
                .fillItem(
                        new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 9)
                                .name("")
                                .lore(
                                        new LoreBuilder()
                                                .addLine("")
                                )
                                .build()
                )
                .fillSlots(0, 8)
                .cancellFill(true)
                .addItem(
                        13,
                        new ItemBuilder(Material.GLOWSTONE_DUST)
                                .name("&bSimple test!")
                                .lore(
                                        new LoreBuilder()
                                                .addLines("&7This is a simple test for item menu!", "", "&bEnjoy!")
                                                .colorize()
                                )
                                .build()
                )
                .addButton(
                        new SimpleButton(
                                13,
                                event -> {
                                    Player eventPlayer = (Player) event.getWhoClicked();
                                    eventPlayer.playSound(eventPlayer.getLocation(), Sound.LEVEL_UP, 1, 1);

                                    return true;
                                }
                        )
                )
                .openEvent(event -> {
                    Player eventPlayer = (Player) event.getPlayer();
                    eventPlayer.sendMessage("Opening...!");

                    return false;
                })
                .closeEvent(event -> {
                    Player eventPlayer = (Player) event.getPlayer();
                    eventPlayer.sendMessage("Closing...!");
                });

        player.openInventory(menuBuilder.build());

        return true;
    }

    @ACommand(names = "firework")
    public boolean fireworkCommand(@Injected(true) CommandSender sender) {
        Player player = (Player) sender;

        ItemStack firework = new FireworkBuilder(Material.FIREWORK)
                .addAttribute(
                        new FireworkAttributes()
                                .color(Color.BLUE)
                                .flicker(true)
                                .trail(true)
                                .colorFade(Color.LIME)
                                .type(FireworkEffect.Type.STAR)
                )
                .power(4)
                .buildFirework();

        player.getInventory().addItem(firework);

        return true;
    }

}