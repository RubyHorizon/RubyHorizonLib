# Commands

Shaded [Command api](https://docs.commandapi.dev/)
is used for configuration.

Shaded path:
> team.rubyhorizon.lib.commandapi

```java
// Create our command
new CommandAPICommand("broadcastmsg")
    .withArguments(new GreedyStringArgument("message")) // The arguments
    .withAliases("broadcast", "broadcastmessage")       // Command aliases
    .withPermission(CommandPermission.OP)               // Required permissions
    .executes((sender, args) -> {
        String message = (String) args.get("message");
        Bukkit.getServer().broadcastMessage(message);
    })
    .register();
```

More information is available at this [link](https://docs.commandapi.dev/).

[Back to information page](../info.md)