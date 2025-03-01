package sqidy.economyPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sqidy.economyPlugin.EconomyPlugin;
import sqidy.economyPlugin.handlers.ConfigHandler;

public class HostileMobKillListener implements Listener {
    public HostileMobKillListener(EconomyPlugin plugin) { Bukkit.getPluginManager().registerEvents(this, plugin); }

    @EventHandler
    public void onHostileMobKill(PlayerJoinEvent event){

    }
}
