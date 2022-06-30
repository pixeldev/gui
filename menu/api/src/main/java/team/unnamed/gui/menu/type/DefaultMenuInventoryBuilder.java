package team.unnamed.gui.menu.type;

public class DefaultMenuInventoryBuilder
        extends MenuInventoryBuilderLayout<DefaultMenuInventoryBuilder> {

    protected DefaultMenuInventoryBuilder(String title) {
        super(title);
    }

    protected DefaultMenuInventoryBuilder(String title, int rows) {
        super(title, rows);
    }

    @Override
    protected DefaultMenuInventoryBuilder back() {
        return this;
    }

}
