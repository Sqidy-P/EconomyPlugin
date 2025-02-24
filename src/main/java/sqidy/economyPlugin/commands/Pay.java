package sqidy.economyPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static sqidy.economyPlugin.handlers.BalanceHandler.*;
import static sqidy.economyPlugin.utils.EconomyUtils.isFloat;

public class Pay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You must be a player to execute this command!");
        }

        Player player = (Player) sender;

        if (args.length == 2 && player.hasPermission("economy.pay") && isFloat(args[1])){
            float amount = Float.parseFloat(args[1]);
            String targetPlayer = args[0];
            float currentBalance = Float.parseFloat(getBalance(player.getName()));

            if (currentBalance < amount){
                return false;
            }

            addToBalance(targetPlayer, amount);
            takeFromBalance(player.getName(), amount);
            return true;
        }

        return false;
    }
}
