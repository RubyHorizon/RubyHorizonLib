package team.rubyhorizon.lib.utils.builders.item.meta;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@RequiredArgsConstructor
public class LoreMetaBuilder implements MetaBuilder {
    private final List<Component> lore;

    @Override
    public void apply(ItemMeta meta) {
        meta.lore(lore);
    }
}