package team.rubyhorizon.lib.configurate;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;


@NoArgsConstructor
public abstract class ConfigHolder<T> {

    @SneakyThrows
    public void loadOrCreateConfig(File configFile, TypeSerializerCollection.Builder serializers) {
        loadOrCreateConfig(configFile, serializers.registerAll(TypeSerializerCollection.defaults()).build());
    }

    @SneakyThrows
    public void loadOrCreateConfig(File configFile, TypeSerializerCollection serializers) {
        YamlConfigurationLoader configLoader =
                buildConfigLoader(configFile, ConfigurationOptions.defaults().serializers(serializers));
        CommentedConfigurationNode node = configLoader.load();

        Class<T> genericType = getGenericType();

        if (!configFile.exists()) {
            configFile.createNewFile();
            T value = createObjectByClass(genericType);
            node.set(genericType, value);
            configLoader.save(node);
        }

        copyFields(Objects.requireNonNull(node.get(genericType)), this);
    }

    private YamlConfigurationLoader buildConfigLoader(File file, ConfigurationOptions options) {
        return YamlConfigurationLoader.builder()
                .nodeStyle(NodeStyle.BLOCK)
                .file(file)
                .defaultOptions(options)
                .build();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private T createObjectByClass(Class<T> clazz) {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return (T) constructor.newInstance();
            }
        }
        throw new IllegalArgumentException("Class must have no-args constructor!");
    }

    private static void copyFields(Object source, Object target) {
        Class<?> clazz = source.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = field.getModifiers();

            if (Modifier.isTransient(modifiers) || Modifier.isFinal(modifiers)) {
                continue;
            }

            field.setAccessible(true);
            try {
                Object value = field.get(source);
                field.set(target, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed copy field: " + field.getName(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericType() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
