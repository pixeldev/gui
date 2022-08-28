package team.unnamed.gui.item.skull;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MineskinSkinProvider implements SkinProvider {

    private static final URL URL_GENERATE_URL;

    static {
        try {
            URL_GENERATE_URL = new URL("https://api.mineskin.org/generate/url");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable SkullSkin fetchSkin(Type type, String key) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) URL_GENERATE_URL.openConnection();

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "unnamed-gui");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);

        try (OutputStream out = connection.getOutputStream()) {
            byte[] data = ("{ \"url\": \"" + key + "\" }").getBytes(StandardCharsets.UTF_8);
            out.write(data);
        }

        JsonObject jsonResponse;

        // execute request and read response
        try (Reader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            jsonResponse = PARSER.parse(reader).getAsJsonObject();
        }

        JsonObject data = jsonResponse.getAsJsonObject("data");

        JsonObject texture = data.getAsJsonObject("texture");
        String signature = texture.get("signature").getAsString();
        String value = texture.get("value").getAsString();
        return new SkullSkin(signature, value);
    }
}
