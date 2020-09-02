package team.unnamed.gui.item.type;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class SkullBuilder extends DefaultItemBuilder {

    private String owner;
    private String url;

    SkullBuilder(Material material, int amount, byte data) {
        super(material, amount, data);
    }

    SkullBuilder(Material material, int amount) {
        super(material, amount);
    }

    SkullBuilder(Material material) {
        super(material);
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
        } else {
            if (url != null) {

                GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                byte[] encondedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());

                profile.getProperties().put("textures", new Property("textures", new String(encondedData)));

                Field profileField = null;

                try {
                    profileField = skullMeta.getClass().getDeclaredField("profile");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                if (profileField != null) {
                    profileField.setAccessible(true);

                    try {
                        profileField.set(skullMeta, profile);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

}