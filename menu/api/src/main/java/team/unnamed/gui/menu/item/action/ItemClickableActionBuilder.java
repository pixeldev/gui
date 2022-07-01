package team.unnamed.gui.menu.item.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static team.unnamed.validate.Validate.isNotNull;

public class ItemClickableActionBuilder {

    protected ItemClickableActionBuilder() {
    }

    public Multiple multipleAction() {
        return new Multiple();
    }

    public ItemClickableAction globalAction(Predicate<Inventory> action) {
        return new SingleClickableAction(isNotNull(
                action,
                "Action cannot be null."
        ));
    }

    public static class Multiple {

        private final Map<ClickType, Predicate<Inventory>> actions;

        public Multiple() {
            this.actions = new HashMap<>();
        }

        public Multiple link(ClickType clickType, Predicate<Inventory> action) {
            actions.put(
                    isNotNull(clickType, "Click type cannot be null."),
                    isNotNull(action, "Action cannot be null.")
            );
            return this;
        }

        public ItemClickableAction build() {
            return new MultipleItemClickableAction(actions);
        }

    }
}
