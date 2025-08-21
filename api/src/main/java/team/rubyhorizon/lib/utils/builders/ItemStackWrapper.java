package team.rubyhorizon.lib.utils.builders.item;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.rubyhorizon.lib.utils.builders.item.meta.MetaBuilder;

import java.util.List;

@Getter
public class ItemStackWrapper {
    private final ItemStack itemStack;

    public ItemStackWrapper(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemStackWrapper(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemStackWrapper(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void applyMeta(List<MetaBuilder> builders) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            for (MetaBuilder builder : builders) builder.apply(meta);
            itemStack.setItemMeta(meta);
        }
    }
}
