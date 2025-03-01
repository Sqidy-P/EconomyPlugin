package sqidy.economyPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import sqidy.economyPlugin.EconomyPlugin;

import java.util.Arrays;

public class CraftingListener implements Listener {
    public CraftingListener(EconomyPlugin plugin) { Bukkit.getPluginManager().registerEvents(this, plugin); }

    @EventHandler
    public void onCraftEvent(PrepareItemCraftEvent event){
        Bukkit.getLogger().info(String.valueOf(event.getInventory().getResult() ));
    }
}
