package team.rubyhorizon.lib.wrappers.events;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class EventsHolder {

    private final Plugin plugin;

    private final List<EventsListener> listeners = new ArrayList<>();

    public EventsHolder(Plugin plugin) {
        this.plugin = plugin;
    }

    public EventsListener createEventsListener() {
        var listener = new EventsListener();
        listeners.add(listener);
        return listener;
    }

    public void removeListeners(EventsListener... listeners) {
        for (var listener: listeners) {
            if (this.listeners.contains(listener)) {

                listener.unregisterAll();

                this.listeners.remove(listener);
            }
        }
    }

    public void removeAllListeners() {
        var clone = new ArrayList<>(listeners);
        for (var listener: clone) {
            listener.unregisterAll();
            this.listeners.remove(listener);
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class EventsListener {

        private final List<Listener> listeners = new ArrayList<>();

        public void registerEvents(Listener... listeners) {
            for (var listener: listeners) {
                this.listeners.add(listener);
                Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
            }
        }

        public void unregisterAll() {
            for (var listener: listeners) {
                HandlerList.unregisterAll(listener);
            }
        }

        public void unregisterEvents(Listener... listeners) {
            for (var listener: listeners) {
                if (this.listeners.contains(listener)) {

                    HandlerList.unregisterAll(listener);

                    this.listeners.remove(listener);
                }
            }
        }
    }
}
