package team.unnamed.gui.example;

import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import team.unnamed.gui.core.GUIListeners;

public class Example extends JavaPlugin {

    public void onEnable() {
        CommandManager commandManager = new BukkitCommandManager(getName());
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new BukkitModule());
        partInjector.install(new DefaultsModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandManager.registerCommands(builder.fromClass(new ExampleCommand()));

        Bukkit.getPluginManager().registerEvents(new GUIListeners(), this);
    }
}