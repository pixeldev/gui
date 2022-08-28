package team.unnamed.gui.item.skull;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AshconSkinProvider implements SkinProvider {

    private static final String BASE_URL = "https://api.ashcon.app/mojang/v2/user/";

    @Override
    public @Nullable SkullSkin fetchSkin(Type type, String key) throws Exception {
        if (type == Type.URL) {
            return null;
        }

        URL url = new URL(BASE_URL + key);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "unnamed-gui");
        connection.setRequestMethod("GET");

        try (Reader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()
        ))) {
            JsonObject json = PARSER.parse(reader).getAsJsonObject();
            JsonObject texturesJson = json.getAsJsonObject("textures");

            if (texturesJson == null) {
                return null;
            }

            JsonObject rawJson = texturesJson.getAsJsonObject("raw");

            String signature = rawJson.get("signature").getAsString();
            String value = rawJson.get("value").getAsString();

            return new SkullSkin(signature, value);
        }
    }
}
