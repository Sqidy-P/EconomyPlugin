package sqidy.economyPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import sqidy.economyPlugin.commands.Balance;
import sqidy.economyPlugin.commands.Pay;
import sqidy.economyPlugin.handlers.FileHandler;
import sqidy.economyPlugin.listeners.FirstJoinListener;
import sqidy.economyPlugin.utils.Config;


public final class EconomyPlugin extends JavaPlugin {
    public EconomyPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        this.getLogger().info("Sqidy's Economy plugin has loaded.");

        // Commands
        this.getCommand("balance").setExecutor(new Balance());
        this.getCommand("pay").setExecutor(new Pay());

        // Listeners
        new FirstJoinListener(this);

        //  Handlers
        FileHandler.setup();
        this.getLogger().info(Config.PLAYTIME_REQUIRED);
    }

    @Override
    public void onDisable() {

    }
}
