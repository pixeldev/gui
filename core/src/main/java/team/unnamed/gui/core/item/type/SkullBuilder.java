package team.unnamed.gui.core.item.type;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import team.unnamed.gui.core.gui.factory.GUIFactory;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class SkullBuilder extends ItemBuilderLayout<SkullBuilder> {

    private static final Field PROFILE_FIELD;

    static {

        try {
            Class<?> metaClass = Class.forName("org.bukkit.craftbukkit.v" + GUIFactory.SERVER_VERSION + ".inventory.CraftMetaSkull");

            PROFILE_FIELD = metaClass.getDeclaredField("profile");
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new IllegalStateException("Cannot get the SkullMeta profile field!", e);
        }

    }

    private String owner;
    private String url;

    protected SkullBuilder(Material material) {
        this(material, 1);
    }

    protected SkullBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    protected SkullBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
    }

    public SkullBuilder setOwner(String owner) {
        this.owner = owner;

        return this;
    }

    public SkullBuilder setUrl(String url) {
        this.url = url;

        return this;
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = super.build();

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        if (owner != null) {
            skullMeta.setOwner(owner);
        } else if (url != null) {
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);

            byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());

            gameProfile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            boolean accessible = PROFILE_FIELD.isAccessible();
            PROFILE_FIELD.setAccessible(true);

            try {
                PROFILE_FIELD.set(skullMeta, gameProfile);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                PROFILE_FIELD.setAccessible(accessible);
            }

        }

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    @Override
    protected SkullBuilder back() {
        return this;
    }

}