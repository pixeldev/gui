package team.unnamed.gui.button;

public class SimpleButton {

    private final int slot;
    private final Button button;

    public SimpleButton(int slot, Button button) {
        this.slot = slot;
        this.button = button;
    }

    public int getSlot() {
        return slot;
    }

    public Button getButton() {
        return button;
    }

}