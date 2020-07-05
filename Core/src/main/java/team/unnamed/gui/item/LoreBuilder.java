package team.unnamed.gui.item;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoreBuilder {

    private List<String> lore;

    public LoreBuilder() {
        lore = new ArrayList<>();
    }

    public LoreBuilder(List<String> lore) {
        this.lore = lore;
    }

    public LoreBuilder set(List<String> lore) {
        this.lore = lore;

        return this;
    }

    public LoreBuilder addLines(String... line) {
        lore.addAll(Arrays.asList(line));

        return this;
    }

    public LoreBuilder addLine(String line) {
        this.lore.add(line);

        return this;
    }

    public LoreBuilder colorize() {
        lore.replaceAll(line -> ChatColor.translateAlternateColorCodes('&', line));

        return this;
    }

    public LoreBuilder replace(String replace, String replacement) {
        lore.replaceAll(line -> line.replace(replace, replacement));

        return this;
    }

    public List<String> build() {
        return lore;
    }

}