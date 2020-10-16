package team.unnamed.gui.item.type;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import team.unnamed.gui.menu.factory.MenuFactory;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class SkullBuilder extends ItemBuilderLayout<SkullBuilder> {
    
    private static final Field PROFILE_FIELD;
    
    static {
        try {
            Class<?> clazz = Class.forName("org.bukkit.craftbukkit.v" + MenuFactory.SERVER_VERSION + ".inventory.CraftMetaSkull");
            PROFILE_FIELD = clazz.getDeclaredField("profile");
        } catch (NoSuchFieldException | ClassNotFoundException e) {
            throw new IllegalStateException("Cannot get the SkullMeta profile field!", e);
        }
    }
    
    private String owner;
    private String url;

    SkullBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
    }

    SkullBuilder(Material material, int amount) {
        this(material, amount, (byte) 0);
    }

    SkullBuilder(Material material) {
        this(material, 1);
    }

    public SkullBuilder owner(String owner) {
        this.owner = owner;
        return this;
    }

    public SkullBuilder url(String url) {
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
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encondedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());

            profile.getProperties().put("textures", new Property("textures", new String(encondedData)));
            boolean accessible = PROFILE_FIELD.isAccessible();
            PROFILE_FIELD.setAccessible(true);
            try {
                PROFILE_FIELD.set(skullMeta, profile);
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