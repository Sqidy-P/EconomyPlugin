package sqidy.economyPlugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import sqidy.economyPlugin.handlers.FileHandler;

public final class EconomyPlugin extends JavaPlugin {
    public EconomyPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        this.getLogger().info("Sqidy's Economy plugin has loaded.");

        // Commands

        // Listeners

        //  Handlers
        FileHandler.setup();
    }

    @Override
    public void onDisable() {

    }
}
