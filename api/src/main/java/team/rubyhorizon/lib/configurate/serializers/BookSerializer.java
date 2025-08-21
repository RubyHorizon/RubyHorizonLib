package team.rubyhorizon.lib.configurate.serializers;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class BookSerializer implements TypeSerializer<Book> {

    @Override
    public Book deserialize(@NotNull Type type, @NotNull ConfigurationNode node) throws SerializationException {
        String title = node.node("title").getString();
        String author = node.node("author").getString();
        List<String> pagesJson = node.node("pages").getList(String.class);
        List<Component> pages = pagesJson != null
                ? pagesJson.stream().map(MiniMessage.miniMessage()::deserialize).collect(Collectors.toList())
                : List.of();
        return Book.book(MiniMessage.miniMessage().deserialize(title), MiniMessage.miniMessage().deserialize(author), pages);
    }

    @Override
    public void serialize(@NotNull Type type, @Nullable Book obj, @NotNull ConfigurationNode node) throws SerializationException {
        if (obj == null) {
            node.raw(null);
            return;
        }
        node.node("title").set(String.class, MiniMessage.miniMessage().serialize(obj.title()));
        node.node("author").set(String.class, MiniMessage.miniMessage().serialize(obj.author()));
        List<String> pagesJson = obj.pages().stream()
                .map(MiniMessage.miniMessage()::serialize)
                .collect(Collectors.toList());
        node.node("pages").setList(String.class, pagesJson);
    }
}
