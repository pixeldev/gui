package team.unnamed.gui.item.skull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class SkinManager {

    private final Map<String, SkullSkin> skins;
    private final SkinProvider provider;
    private final Executor executor;

    public SkinManager(SkinProvider provider, Executor executor) {
        this.provider = provider;
        this.executor = executor;
        this.skins = new ConcurrentHashMap<>();
    }

    public @Nullable SkullSkin getSkin(String key) {
        return skins.get(key);
    }

    public CompletableFuture<SkullSkin> fetchSkin(
            SkinProvider.Type type,
            String key
    ) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SkullSkin skin = skins.get(key);

                if (skin == null) {
                    skin = provider.fetchSkin(type, key);
                }

                if (skin == null) {
                    return null;
                }

                if (!skins.containsKey(key)) {
                    skins.put(key, skin);
                }

                return skin;
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, executor);
    }

    public void setSkin(String key, SkullSkin skin) {
        skins.put(key, skin);
    }

    public @Nullable SkullSkin removeSkin(String key) {
        return skins.remove(key);
    }

}
