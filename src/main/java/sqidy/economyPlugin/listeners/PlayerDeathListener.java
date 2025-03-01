package sqidy.economyPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import sqidy.economyPlugin.EconomyPlugin;

import static sqidy.economyPlugin.handlers.BalanceHandler.takeFromBalance;
import static sqidy.economyPlugin.utils.Config.ON_PLAYER_DEATH_AMOUNT;
import static sqidy.economyPlugin.utils.Config.ON_PLAYER_DEATH_ENABLED;

public class PlayerDeathListener implements Listener {
    public PlayerDeathListener(EconomyPlugin plugin) { Bukkit.getPluginManager().registerEvents(this, plugin); }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        String playerName = event.getPlayer().getName();

        if (ON_PLAYER_DEATH_ENABLED){
            takeFromBalance(playerName, ON_PLAYER_DEATH_AMOUNT);
        }
    }
}
