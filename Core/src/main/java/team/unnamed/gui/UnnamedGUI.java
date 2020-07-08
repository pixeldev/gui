package team.unnamed.gui;

import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.gui.listeners.MenuListeners;

public class UnnamedGUI extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MenuListeners(), this);
    }

}