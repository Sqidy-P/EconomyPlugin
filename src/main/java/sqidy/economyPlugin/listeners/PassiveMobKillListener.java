package sqidy.economyPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sqidy.economyPlugin.EconomyPlugin;

public class PassiveMobKillListener implements Listener {
    public PassiveMobKillListener(EconomyPlugin plugin) { Bukkit.getPluginManager().registerEvents(this, plugin); }

    @EventHandler
    public void onPassiveMobKill(PlayerJoinEvent event){

    }
}
