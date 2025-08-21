package team.rubyhorizon.lib.utils.builders.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import team.rubyhorizon.lib.utils.builders.item.meta.*;

import java.util.ArrayList;
import java.util.List;

// By Loft69
public class ItemBuilder {
    private final team.rubyhorizon.lib.utils.builders.item.ItemStackWrapper itemStackWrapper;
    private final List<MetaBuilder> metaBuilders = new ArrayList<>();

    public ItemBuilder(Material material) {
        this.itemStackWrapper = new team.rubyhorizon.lib.utils.builders.item.ItemStackWrapper(material);
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStackWrapper = new team.rubyhorizon.lib.utils.builders.item.ItemStackWrapper(material, amount);
    }

    public ItemBuilder(ItemStack item) {
        this.itemStackWrapper = new team.rubyhorizon.lib.utils.builders.item.ItemStackWrapper(item);
    }

    public ItemBuilder name(Component name) {
        metaBuilders.add(new NameMetaBuilder(name));
        return this;
    }

    public ItemBuilder lore(List<Component> lore) {
        metaBuilders.add(new LoreMetaBuilder(lore));
        return this;
    }

    public ItemBuilder customModelData(int value) {
        metaBuilders.add(new CustomModelDataMetaBuilder(value));
        return this;
    }

    public <T, C> ItemBuilder tag(String key, C value, PersistentDataType<T, C> type, JavaPlugin instance) {
        metaBuilders.add(new TagMetaBuilder<>(key, value, type, instance));
        return this;
    }

    public ItemStack build() {
        itemStackWrapper.applyMeta(metaBuilders);
        return itemStackWrapper.getItemStack();
    }

    public static ItemBuilder builder(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder builder(Material material, int amount) {
        return new ItemBuilder(material, amount);
    }
}
