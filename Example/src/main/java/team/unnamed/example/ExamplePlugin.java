package team.unnamed.example;

import me.fixeddev.ebcm.bukkit.BukkitCommandManager;
import me.fixeddev.ebcm.parametric.ParametricCommandBuilder;
import me.fixeddev.ebcm.parametric.ReflectionParametricCommandBuilder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import team.unnamed.gui.MenuListeners;

public class ExamplePlugin extends JavaPlugin {

    public void onEnable() {
        registerCommands();

        Bukkit.getPluginManager().registerEvents(new MenuListeners(), this);
    }

    private void registerCommands() {
        ParametricCommandBuilder builder = new ReflectionParametricCommandBuilder();
        BukkitCommandManager manager = new BukkitCommandManager(getName());

        manager.registerCommands(builder.fromClass(new ExampleCommand()));
    }

}