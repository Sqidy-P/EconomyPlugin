package sqidy.economyPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import sqidy.economyPlugin.handlers.FileHandler;


public final class EconomyPlugin extends JavaPlugin {
    public EconomyPlugin plugin;

    public String configDir = "./plugins/EconomyPlugin/config.yml";
    public String accountsDir = "./plugins/EconomyPlugin/accounts.yml";

    @Override
    public void onEnable() {
        plugin = this;

        this.getLogger().info("Sqidy's Economy plugin has loaded.");

        // Commands

        // Listeners

        //  Handlers
        FileHandler.setup();

        // Sqidy is poor so he doesn't get any money
        FileHandler.appendNewAccount("UUID", "sqidys", "0.00", accountsDir);
    }

    @Override
    public void onDisable() {

    }
}
