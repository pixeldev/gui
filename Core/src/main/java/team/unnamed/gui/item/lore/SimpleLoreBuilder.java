package team.unnamed.gui.item.lore;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleLoreBuilder implements LoreBuilder {

    private final List<String> lore;

    SimpleLoreBuilder() {
        lore = new ArrayList<>();
    }

    SimpleLoreBuilder(List<String> lore) {
        this.lore = lore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoreBuilder addLines(String... lines) {
        lore.addAll(Arrays.asList(lines));

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoreBuilder colorize() {
        lore.replaceAll(line -> ChatColor.translateAlternateColorCodes('&', line));

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoreBuilder replace(String replace, String replacement) {
        lore.replaceAll(line -> line.replace(replace, replacement));

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> build() {
        return lore;
    }

}