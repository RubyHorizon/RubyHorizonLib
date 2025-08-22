package team.rubyhorizon.lib;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import team.rubyhorizon.lib.commands.RhLibCommand;
import team.rubyhorizon.lib.metrics.BstatsMetrics;

public final class RubyHorizonLibPlugin extends JavaPlugin {

    private BstatsMetrics bstatsMetrics;

    private static final String logoMessage = """
            \s
            -----------------------------------
             _____  _     _      _ _    \s
            |  __ \\| |   | |    (_) |   \s
            | |__) | |__ | |     _| |__ \s
            |  _  /| '_ \\| |    | | '_ \\\s
            | | \\ \\| | | | |____| | |_) |
            |_|  \\_\\_| |_|______|_|_.__/\s
            
            By Shershnyaga_, Salatosik, Loft69
            -----------------------------------
            """;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        bstatsMetrics = new BstatsMetrics(this, 27004);

        new RhLibCommand().get().register(this);

        Bukkit.getLogger().info(logoMessage);
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
        bstatsMetrics.shutdown();
    }
}
