package team.unnamed.gui.core.item.type;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.unnamed.gui.abstraction.item.nbt.ItemStackNBT;
import team.unnamed.gui.core.gui.factory.ItemStackNBTFactory;
import team.unnamed.gui.core.item.flag.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.unnamed.gui.core.version.ServerVersionProvider.SERVER_VERSION_INT;
import static team.unnamed.validate.Validate.isNotNull;
import static team.unnamed.validate.Validate.isState;

abstract class ItemBuilderLayout<T extends ItemBuilder> implements ItemBuilder {

    protected final Material material;
    private final int amount;
    private final short data;

    private String name;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<ItemFlag> flags = new ArrayList<>();
    private boolean removalNBT;

    protected ItemBuilderLayout(Material material, int amount, short data) {
        this.material = material;
        this.amount = amount;
        this.data = data;
    }

    @Override
    public T setName(String name) {
        this.name = isNotNull(name, "Item name can't be null.");
        return back();
    }

    @Override
    public T setLore(List<String> lore) {
        this.lore = isNotNull(lore, "Item lore can't be null.");
        return back();
    }

    @Override
    public T setEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = isNotNull(enchantments, "Item enchantments can't be null.");
        return back();
    }

    @Override
    public T addEnchant(Enchantment enchantment, int level) {
        isNotNull(enchantment, "Enchantment to add can't be null.");
        isState(level >= 0, "Enchantment level must be higher or equals than 0.");

        enchantments.put(enchantment, level);
        return back();
    }

    @Override
    public T setFlags(List<ItemFlag> flags) {
        this.flags = isNotNull(flags, "Item flags can't be null.");
        return back();
    }

    @Override
    public T addFlag(ItemFlag flag) {
        isNotNull(flag, "Flag can't be null");

        flags.add(flag);
        return back();
    }

    @Override
    public T setRemovalNBT(boolean removal) {
        this.removalNBT = removal;
        return back();
    }

    @Override
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount, data);
        ItemMeta meta = itemStack.getItemMeta();

        enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

        meta.setDisplayName(name);
        meta.setLore(lore);

        if (SERVER_VERSION_INT != 7) {
            List<org.bukkit.inventory.ItemFlag> itemFlags = new ArrayList<>();

            if (SERVER_VERSION_INT <= 13) {
                flags.remove(ItemFlag.HIDE_DYE);
            }

            for (ItemFlag itemFlag : flags) {
                itemFlags.add(org.bukkit.inventory.ItemFlag.valueOf(itemFlag.name()));
            }

            itemFlags.forEach(meta::addItemFlags);
        }

        itemStack.setItemMeta(meta);

        if(removalNBT) {
            ItemStackNBT nbt = ItemStackNBTFactory.getInstance();
            itemStack = nbt.applyTag(itemStack, "UNNAMEDGUI");
        }

        return itemStack;
    }

    protected abstract T back();

}