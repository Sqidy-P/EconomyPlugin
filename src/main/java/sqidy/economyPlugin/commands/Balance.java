package sqidy.economyPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sqidy.economyPlugin.handlers.FileHandler;

public class Balance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId().toString();

        // If no args, return user balance
        if (args.length == 0){
            String currentBalance = FileHandler.getBalance(playerUUID);
            player.sendMessage("You currently have $" + currentBalance + "!");
            return true;
        }

        // If 1 arg, return false;
        // unneeded. it'll do it automatically
        player.sendMessage("Balance command used with " + args.length);
        // If 3 arg
        if (args.length == 3) {
            // check what arg and permission
            if (args[0].equalsIgnoreCase("give") && player.hasPermission("economy.give")){
                Bukkit.getLogger().info("GIVE COMMAND USED");

                String TargetPlayer;
                String TargetPlayerUUID;

                if (Bukkit.getPlayer(args[1]).isOnline()){
                    Bukkit.getLogger().info("PLAYER IS ONLINE");
                    TargetPlayer = Bukkit.getPlayer(args[1]).getName();
                    TargetPlayerUUID = Bukkit.getPlayerUniqueId(args[1]).toString();

                    if (!(Float.parseFloat(args[2]) >= 0.00)){
                        Bukkit.getLogger().info("NUMBER IS NOT FLOAT");
                        return false;
                    }

                    float amountToAdd = Float.parseFloat(args[2]);

                    FileHandler.addToBalance(TargetPlayerUUID, TargetPlayer, amountToAdd);

                    Bukkit.getLogger().info("MONEY ADDED");
                    return true;
                }

                return false;
            }
            // if they have permission, and there is an ign, follow through
        }

        return true;
    }
}
