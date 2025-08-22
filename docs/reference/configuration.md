# Configuration

Shaded [SpongePowered/Configuration](https://docs.spongepowered.org/stable/en/server/getting-started/configuration/index.html) 
is used for configuration.

Shaded path:
> team.rubyhorizon.lib.configurate

To create your own config, you need to create a configuration class
```java
import team.rubyhorizon.lib.configurate.objectmapping.ConfigSerializable;
import team.rubyhorizon.lib.configurate.serialize.TypeSerializerCollection;
import team.rubyhorizon.lib.configurate.ConfigHolder;
import net.kyori.adventure.text.Component;

@ConfigSerializable
@NoArgsConstructor
@Getter
public class Config extends ConfigHolder<Config> {
    
    private String someString = "aboba";
    private int someNumber = 1;
    private Component kyoriComponent = Component.text("Component Text");
    
}
```

**No args constructor on your config class is required!**

Next, you need to create an instance of this class and write loadOrCreateConfig
```java
Config config = new Config();
config.loadOrCreateConfig(new File("pathToConfigFile.yml"), TypeSerializerCollection.builder().register(Component.class, new ComponentSerializer()));
```
Custom type serializers are passed to *TypeSerializerCollection.builder()*.
```java
 TypeSerializerCollection.builder()
        // Register Kyori Components Serializer
        .register(Component.class, new ComponentSerializer())
        // Register Book serializer
        .register(BookSerializer.class, new BookSerializer())
        // Register Title serializer
        .register(BookSerializer.class, new BookSerializer())
        // For enums you need to register GenericEnumSerializer
        .register(YourEnum.class, new GenericEnumSerializer<>(YourEnum::values));
```

Next, you can use the previously created config class to get data from it.
```java
config.someString();
config.someNumber();
config.getKyoriComponent();
```

[Back to information page](../info.md)