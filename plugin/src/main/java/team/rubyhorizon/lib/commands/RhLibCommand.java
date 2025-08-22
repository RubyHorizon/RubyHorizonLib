package team.rubyhorizon.lib.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.command.CommandSender;

public class RhLibCommand {

    private final CommandAPICommand command;

    public RhLibCommand() {
        command = new CommandAPICommand("rhlib");
        command.withArguments(new GreedyStringArgument("ping"))
                .withPermission(CommandPermission.OP)
                .executes((sender, args) -> {
                    sender.sendMessage("pong"); return 0;
                });
    }

    public CommandAPICommand get() {
        return command;
    }
}
