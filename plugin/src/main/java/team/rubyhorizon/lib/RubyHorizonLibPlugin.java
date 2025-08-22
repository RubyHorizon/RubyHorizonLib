package team.rubyhorizon.lib;

import org.bukkit.plugin.java.JavaPlugin;
import team.rubyhorizon.lib.metrics.BstatsMetrics;

public final class RubyHorizonLibPlugin extends JavaPlugin {

    private BstatsMetrics bstatsMetrics;

    @Override
    public void onEnable() {
        bstatsMetrics = new BstatsMetrics(this, 27004);
    }

    @Override
    public void onDisable() {
        bstatsMetrics.shutdown();
    }
}
