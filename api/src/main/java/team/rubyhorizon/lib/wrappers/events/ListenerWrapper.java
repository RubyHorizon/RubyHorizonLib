package team.rubyhorizon.lib.wrappers.events;

import lombok.NoArgsConstructor;
import org.bukkit.event.Listener;

@NoArgsConstructor
public abstract class ListenerWrapper implements Listener {

    private EventsHolder.EventsListener eventsListener;

    public void register(EventsHolder.EventsListener eventsListener) {
        this.eventsListener = eventsListener;
        eventsListener.registerEvents(this);
    }

    public void unregister() {
        if (eventsListener != null) {
            eventsListener.unregisterEvents(this);
        }
    }
}
