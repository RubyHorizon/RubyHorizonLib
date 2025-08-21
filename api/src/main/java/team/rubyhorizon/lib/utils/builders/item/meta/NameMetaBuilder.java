package team.rubyhorizon.lib.utils.builders.item.meta;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class NameMetaBuilder implements MetaBuilder {
    private final Component name;

    @Override
    public void apply(ItemMeta meta) {
        meta.displayName(name);
    }
}
