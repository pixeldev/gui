package team.unnamed.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import team.unnamed.gui.listeners.MenuListeners;

import java.util.HashSet;
import java.util.Set;

public class MenuManager {

    private final Set<MenuBuilder> menuBuilders = new HashSet<>();

    public Set<MenuBuilder> getMenuBuilders() {
        return menuBuilders;
    }

    public void registerListener(Plugin plugin, MenuManager menuManager) {
        Bukkit.getServer().getPluginManager().registerEvents(new MenuListeners(menuManager), plugin);
    }

    public void registerMenu(MenuBuilder menuBuilder) {
        menuBuilders.add(menuBuilder);
    }

}