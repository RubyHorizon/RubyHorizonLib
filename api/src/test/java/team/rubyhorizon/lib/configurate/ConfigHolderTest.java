package team.rubyhorizon.lib.configurate;

import org.junit.jupiter.api.Test;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;
import team.rubyhorizon.lib.configurate.serializers.ComponentSerializer;

import java.awt.*;
import java.io.File;

public class ConfigHolderTest {

    private final Config config = new Config();

    @Test
    void testCreate() {
        config.loadOrCreateConfig(new File("config_test.yml"), TypeSerializerCollection.builder()
                .register(Component.class, new ComponentSerializer()));
        System.out.println(config.test1);
        System.out.println(config.test2);
        System.out.println(config.test3);
    }

    @ConfigSerializable
    private static class Config extends ConfigHolder<Config> {

        private String test1 = "aboba";
        private int test2 = 1;
        private boolean test3 = true;

        public Config() {

        }
    }

}
