package sqidy.economyPlugin.handlers;

import org.bukkit.Bukkit;

import java.util.HashMap;

import static sqidy.economyPlugin.handlers.FileHandler.*;
import static sqidy.economyPlugin.utils.EconomyUtils.accountsDir;

public class BalanceHandler {
    private static void changeBalance(String playerName, float amount){
        String uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId().toString();

        //region Get data, make sure it's only ~.xx, then modify it in accounts.yml
        HashMap<String, HashMap<String, String>> data = loadDataFromYAML(accountsDir);

        float currentBalance = Float.parseFloat(data.get(uuid).get("balance"));
        float newBalance = Float.parseFloat(String.format("%.2f", (currentBalance + amount)));

        if (newBalance <= 0.009f){
            newBalance = 0.00f;
        }

        modifyAccountData(uuid, playerName, newBalance, false);
        //endregion
    }



    public static void setBalance(String playerName, float amount){
        String uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId().toString();

        modifyAccountData(uuid, playerName, amount, false);
    }

    public static void addToBalance(String playerName, float amount){
        if (amount < -0.00){
            amount = -amount;
        }

        changeBalance(playerName, amount);
    }

    public static void takeFromBalance(String playerName, float amount){
        if (!(amount < 0.00)){
            amount = -amount;
        }

        changeBalance(playerName, amount);
    }

    public static String getBalance(String playerName){
        String uuid = Bukkit.getOfflinePlayer(playerName).getUniqueId().toString();

        return loadDataFromYAML(accountsDir).get(uuid).get("balance");
    }
}
