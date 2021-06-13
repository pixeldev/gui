package team.unnamed.gui.core.gui.type;

import org.bukkit.inventory.Inventory;

import team.unnamed.gui.abstraction.item.ItemClickable;

import java.util.*;

import static team.unnamed.validate.Validate.*;

public class StringLayoutGUIBuilder extends GUIBuilderLayout<StringLayoutGUIBuilder> {

  private final Map<Character, ItemClickable> layoutValues;
  private final List<String> layoutLines;

  protected StringLayoutGUIBuilder(String title) {
    super(title);

    layoutValues = new HashMap<>();
    layoutLines = new ArrayList<>();
  }

  protected StringLayoutGUIBuilder(String title, int rows) {
    super(title, rows);

    layoutValues = new HashMap<>();
    layoutLines = new ArrayList<>();
  }

  public StringLayoutGUIBuilder setLayoutItem(char identifier, ItemClickable item) {
    notNull(item, "Item can't be null.");

    layoutValues.put(identifier, item);

    return this;
  }

  public StringLayoutGUIBuilder setLayoutLines(String... lines) {
    notNull(lines, "Layout lines can't be null.");

    for (String line : lines) {
      addLayoutLine(line);
    }

    return this;
  }

  public StringLayoutGUIBuilder addLayoutLine(String line) {
    validateLine(line);

    layoutLines.add(line.trim());

    return this;
  }

  private void validateLine(String line) {
    notNull(line, "Layout line can't be null.");
    argument(line.length() <= 9, "Cannot add layout line '" + line + "' because has more than 9 characters.");
  }

  @Override
  public Inventory build() {
    int index = 0;

    for (String layoutLine : layoutLines) {
      for (int i = 0; i < layoutLine.length(); i++) {
        char layoutIdentifier = layoutLine.charAt(i);
        ItemClickable itemClickable = layoutValues.get(layoutIdentifier);

        if (itemClickable == null) {
        	index++;

        	continue;
				}

        addItem(itemClickable.cloneInSlot(index++));
      }
    }

    return super.build();
  }

  @Override
  protected StringLayoutGUIBuilder back() {
    return this;
  }

}