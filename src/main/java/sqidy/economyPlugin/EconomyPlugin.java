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

        //region Debugging
        // Add account
        FileHandler.modifyAccountData("TestUUID", "0.00", true);
        FileHandler.modifyAccountData("TestUUIDTwo", "0.00", true);
        // Add x float amount to x user (/pay <amount> <optional IGN>)
        FileHandler.addToBalance("TestUUID", 0.303F);
        FileHandler.addToBalance("TestUUIDTwo", 21.03F);
        // Sets a player's balance (/setBal <IGN> <amount>)
        FileHandler.modifyAccountData("TestUUIDTwo", "50.00", false);
        //endregion
    }

    @Override
    public void onDisable() {

    }
}
