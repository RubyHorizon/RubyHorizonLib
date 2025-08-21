package team.rubyhorizon.lib.configurate.serializers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import team.rubyhorizon.lib.utils.TitleBuilder;

import java.lang.reflect.Type;
import java.time.Duration;

public class TitleSerializer implements TypeSerializer<Title> {

    private final static String TITLE = "title";
    private final static String SUBTITLE = "subtitle";

    private final static String FADE_IN = "fade-in";
    private final static String STAY_IN = "stay-in";
    private final static String FADE_OUT = "fade-out";


    @Override
    public Title deserialize(@NotNull Type type, @NotNull ConfigurationNode node) throws SerializationException {

        Component title = node.node(TITLE).get(Component.class);
        Component subtitle = node.node(SUBTITLE).get(Component.class);

        long fadein = node.node(FADE_IN).getLong();
        long stayIn = node.node(STAY_IN).getLong();
        long fadeOut = node.node(FADE_OUT).getLong();



        return new TitleBuilder()
                .title(title, subtitle)
                .duration(Title.Times.times(Duration.ofMillis(fadein), Duration.ofMillis(stayIn),
                        Duration.ofMillis(fadeOut)))
                .build();
    }

    @Override
    public void serialize(@NotNull Type type, @Nullable Title obj, @NotNull ConfigurationNode node)
            throws SerializationException {
        node.node(TITLE).set(Component.class, obj.title());
        node.node(SUBTITLE).set(Component.class, obj.subtitle());

        Title.Times times = obj.times();

        node.node(FADE_IN).set(calcMillis(times.fadeIn()));
        node.node(STAY_IN).set(calcMillis(times.fadeIn()));
        node.node(FADE_OUT).set(calcMillis(times.fadeIn()));

    }

    private static long calcMillis(Duration duration) {
        return duration.getSeconds() * 1000 + duration.getNano() / 1000000;
    }
}
