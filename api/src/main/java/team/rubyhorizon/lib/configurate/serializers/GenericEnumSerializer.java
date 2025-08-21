package team.rubyhorizon.lib.configurate.serializers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.function.Supplier;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenericEnumSerializer <E extends Enum<E>> implements TypeSerializer<E> {
    E[] enumVariables;

    public GenericEnumSerializer(Supplier<E[]> allEnumVariablesSupplier) {
        enumVariables = allEnumVariablesSupplier.get();
    }

    @Override
    public E deserialize(@NotNull Type type, ConfigurationNode node) throws SerializationException {
        String enumName = node.getString();
        if (enumName == null) {
            throw new SerializationException();
        }
        for (E enumVariable : enumVariables) {
            if (enumVariable.name().equals(enumName)) {
                return enumVariable;
            }
        }
        throw new SerializationException();
    }

    @Override
    public void serialize(@NotNull Type type, @Nullable E obj, @NotNull ConfigurationNode node) throws SerializationException {
        if (obj != null) {
            node.set(obj.name());
        } else {
            throw new SerializationException();
        }
    }
}
