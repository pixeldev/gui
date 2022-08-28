package team.unnamed.gui.item.skull;

public class SkullSkin {

    private final String signature;
    private final String value;

    public SkullSkin(String signature, String value) {
        this.signature = signature;
        this.value = value;
    }

    public String getSignature() {
        return signature;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SkullSkin{" +
                "signature='" + signature + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
