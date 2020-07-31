package team.unnamed.gui.item.lore;

import java.util.List;

public interface LoreBuilder {

    /**
     * Adds the specified lines into the lore
     *
     * @param lines The lines to add
     * @return The same instance of LoreBuilder
     */
    LoreBuilder addLines(String... lines);

    /**
     * Uses {@link org.bukkit.ChatColor#translateAlternateColorCodes(char, String)} to replace all the color codes(&) with actual colors
     * on all the lines of the lore
     *
     * @return The same instance of LoreBuilder
     */
    LoreBuilder colorize();

    /**
     * Replaces the specified {@linkplain String} with another on all the lines of the lore
     *
     * @param replace     The {@linkplain String} to replace
     * @param replacement The replacement {@linkplain String}
     * @return The same instance of LoreBuilder
     */
    LoreBuilder replace(String replace, String replacement);

    /**
     * Builds the final lore
     *
     * @return The lore
     */
    List<String> build();

    /**
     * Creates a new {@link LoreBuilder} without any initial value
     *
     * @return A new {@linkplain LoreBuilder} instance
     */
    static LoreBuilder newBuilder() {
        return new SimpleLoreBuilder();
    }

    /**
     * Creates a new {@link LoreBuilder} with a initial set of lines
     *
     * @param lore The initial set of lines for the lore
     * @return A new {@linkplain LoreBuilder} instance
     */
    static LoreBuilder newBuilder(List<String> lore) {
        return new SimpleLoreBuilder(lore);
    }

}