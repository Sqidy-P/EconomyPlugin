package sqidy.economyPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static sqidy.economyPlugin.handlers.BalanceHandler.*;
import static sqidy.economyPlugin.utils.EconomyUtils.isFloat;

public class Balance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You must be a player to preform this command!");
        }

        Player player = (Player) sender;

        if (args.length == 3 && isFloat(args[2])){
            float amount = Float.parseFloat(args[2]);
            String targetPlayer = args[1];

            switch (args[0].toLowerCase()){
                case "give":
                    if (player.hasPermission("economy.give")) {
                        addToBalance(targetPlayer, amount);
                        return true;
                    }
                case "take":
                    if (player.hasPermission("economy.take")){
                        takeFromBalance(targetPlayer, amount);
                        return true;
                    }
                default:
                    return false;
            }
        }
        else if (args.length == 2
                && args[0].equalsIgnoreCase("see")
                && player.hasPermission("economy.see")){


            String targetPlayer = args[1];
            String targetPlayerBalance = getBalance(targetPlayer);

            player.sendMessage(targetPlayer + " currently has " + targetPlayerBalance + "!");
            return true;
        }
        else if (args.length == 0){
            String currentBalance = getBalance(player.getName());
            player.sendMessage("You currently have $" + currentBalance + "!");
            return true;
        }

        return false;
    }
}
