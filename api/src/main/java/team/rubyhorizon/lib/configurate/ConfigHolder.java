package team.rubyhorizon.lib.configurate;

import lombok.SneakyThrows;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.lang.reflect.Constructor;

public abstract class ConfigHolder<T> {

    private transient final Class<T> clazz;

    private transient T value;

    public ConfigHolder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SneakyThrows
    public void loadOrCreateConfig(File configFile, TypeSerializerCollection.Builder serializers) {
        loadOrCreateConfig(configFile, serializers.registerAll(TypeSerializerCollection.defaults()).build());
    }

    @SneakyThrows
    public void loadOrCreateConfig(File configFile, TypeSerializerCollection serializers) {
        YamlConfigurationLoader configLoader =
                buildConfigLoader(configFile, ConfigurationOptions.defaults().serializers(serializers));
        CommentedConfigurationNode node = configLoader.load();

        if (!configFile.exists()) {
            configFile.createNewFile();
            value = createObjectByClass(clazz);
            node.set(clazz, value);
            configLoader.save(node);
        }
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

}
