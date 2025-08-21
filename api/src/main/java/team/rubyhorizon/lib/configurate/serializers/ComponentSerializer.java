package team.rubyhorizon.lib.configurate.serializers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public class ComponentSerializer implements TypeSerializer<Component> {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    @Override
    public Component deserialize(@NotNull Type type, ConfigurationNode node) {

        if (node.empty() || node.getString() == null) {
            return Component.empty();
        }

        return MINI_MESSAGE.deserialize(node.getString());
    }

    @Override
    public void serialize(@NotNull Type type, @Nullable Component obj, @NotNull ConfigurationNode node) throws SerializationException {

        if (obj == null) {
            node.set("");
            return;
        }

        node.set(MINI_MESSAGE.serialize(obj));
    }
}
