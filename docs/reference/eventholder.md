# EventHolder

This class was created for the delimited use of
org.bukkit.plugin.Plugin in events register,
and also provides additional functionality
in the form of completing all events registered in this class.

## Listener Wrapper
This class is designed for convenient management of the event system.
```java
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
```

Usage
```java
public SomeListenerClass extends ListenerWrapper {
    
    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent) {
        // TODO Event handle
    }
    
}
```

```java
SomeListenerClass listener = new SomeListenerClass();

EventsHolder eventsHolder = new EventsHolder(yourPluginClass);
EventsListener eventsListener = eventsHolder.createEventsListener();
        
listener.register(eventsListener);
// or
eventsListener.registerEvents(eventsListener);

eventsListener.unregisterEvents(eventsListener);

eventsListener.unregisterAll();

eventsHolder.unregisterAll();
```

[Back to information page](../info.md)