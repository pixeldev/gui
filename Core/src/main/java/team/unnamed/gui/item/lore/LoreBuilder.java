package team.unnamed.gui.item.lore;

import java.util.List;

public interface LoreBuilder {

    LoreBuilder addLines(String... lines);

    LoreBuilder colorize();

    LoreBuilder replace(String replace, String replacement);

    List<String> build();

    static LoreBuilder newBuilder() {
        return new SimpleLoreBuilder();
    }

    static LoreBuilder newBuilder(List<String> lore) {
        return new SimpleLoreBuilder(lore);
    }

}