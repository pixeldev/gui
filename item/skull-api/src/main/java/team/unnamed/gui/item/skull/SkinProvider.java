package team.unnamed.gui.item.skull;

import com.google.gson.JsonParser;
import org.jetbrains.annotations.Nullable;

public interface SkinProvider {

    JsonParser PARSER = new JsonParser();

    SkinProvider MINESKIN = new MineskinSkinProvider();
    SkinProvider ASHCON = new AshconSkinProvider();

    @Nullable SkullSkin fetchSkin(Type type, String key) throws Exception;

    enum Type {
        UUID,
        URL
    }
}
