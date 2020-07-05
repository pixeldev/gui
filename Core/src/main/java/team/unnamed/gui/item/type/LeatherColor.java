package team.unnamed.gui.item.type;

public enum LeatherColor {

    DARK_BLUE(0, 0 ,255),
    LIGHT_BLUE(51, 153, 255),
    LIGHT_RED(255, 0, 0),
    DARK_RED(152, 0, 0),
    CYAN(102, 255, 255),
    YELLOW(255, 255, 0),
    ORANGE(255,128, 0),
    LIME(0 , 255, 0),
    GREEN(0, 204, 0),
    PURPLE(76, 0, 153),
    PINK(255, 153, 255),
    BLACK(0, 0, 0),
    WHITE(255, 255, 255),
    DARK_GRAY(128, 128, 128),
    LIGHT_GRAY(192, 192, 192);

    private final int red;
    private final int green;
    private final int blue;

    LeatherColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

}