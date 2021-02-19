package team.unnamed.gui.core.gui.type;

public class SimpleGUIBuilder extends GUIBuilderLayout<SimpleGUIBuilder> {

    protected SimpleGUIBuilder(String title) {
        super(title);
    }

    protected SimpleGUIBuilder(String title, int rows) {
        super(title, rows);
    }

    @Override
    protected SimpleGUIBuilder back() {
        return this;
    }

}