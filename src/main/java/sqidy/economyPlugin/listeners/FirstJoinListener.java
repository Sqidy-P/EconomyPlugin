package sqidy.economyPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sqidy.economyPlugin.EconomyPlugin;
import sqidy.economyPlugin.handlers.FileHandler;

public class FirstJoinListener implements Listener {
    public FirstJoinListener(EconomyPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFirstTimeJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        String UUID = String.valueOf(Bukkit.getServer().getPlayerUniqueId(player.getName()));
        String playerName = player.getName();

        if (!player.hasPlayedBefore()){
            FileHandler.modifyAccountData(UUID, playerName, 0.00f, true);
        }
    }
}
