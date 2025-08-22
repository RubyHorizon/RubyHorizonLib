package team.rubyhorizon.lib.utils.builders;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.time.Duration;

public class TitleBuilder {

    private Component title = Component.text("");
    private Component subtitle = Component.text("");
    private Title.Times times = Title.Times.times(Duration.ofMillis(2000), Duration.ofMillis(2000), Duration.ofMillis(2000));

    public TitleBuilder title(Component title, Component subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        return this;
    }

    public TitleBuilder duration(Title.Times duration) {
        times = duration;
        return this;
    }

    public Title build() {
        return new Title() {
            @Override
            public @NotNull Component title() {
                return title;
            }

            @Override
            public @NotNull Component subtitle() {
                return subtitle;
            }

            @Override
            public @Nullable Times times() {
                return times;
            }

            @Override
            public <T> @UnknownNullability T part(@NotNull TitlePart<T> part) {
                return null;
            }
        };
    }

}
