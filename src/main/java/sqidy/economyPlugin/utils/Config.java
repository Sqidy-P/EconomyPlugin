package sqidy.economyPlugin.utils;

import sqidy.economyPlugin.handlers.ConfigHandler;

import java.util.HashMap;

import static sqidy.economyPlugin.utils.EconomyUtils.configDir;

public class Config {
    private static final HashMap<String, HashMap<String, HashMap<String, ?>>> configFile = ConfigHandler.loadConfig(configDir);

    


    public static final boolean DEBT_ENABLED = Boolean.parseBoolean(String.valueOf(configFile.get("PlayerDebtEnabled")));


    public static final float FIRST_JOIN_BALANCE = Float.parseFloat(String.valueOf(configFile.get("FirstJoinBalance")));


    public static final boolean HOSTILE_MOB_DEFEAT_ENABLED = Boolean.parseBoolean(String.valueOf(configFile.get("HostileMobDefeat").get("enabled")));
    public static final HashMap<String, ?> HOSTILE_MOB_BALANCE = configFile.get("HostileMobDefeat").get("Mobs");


    public static final boolean PASSIVE_MOB_DEFEAT_ENABLED = Boolean.parseBoolean(String.valueOf(configFile.get("PassiveMobDefeat").get("enabled")));
    public static final HashMap<String, ?> PASSIVE_MOB_BALANCE =  configFile.get("PassiveMobDefeat");


    public static final boolean PLAYTIME_MONEY_ENABLED = Boolean.parseBoolean( String.valueOf(configFile.get("PlaytimeMoney").get("enabled")));
    public static final String PLAYTIME_REQUIRED = String.valueOf(configFile.get("PlaytimeMoney").get("playtimeRequired"));
    public static final float PLAYTIME_BALANCE_ADDED = Float.parseFloat(String.valueOf(configFile.get("PlaytimeMoney").get("balanceAdded")));


    public static final boolean CRAFTING_MONEY_ENABLED = Boolean.parseBoolean( String.valueOf(configFile.get("CraftingMoney").get("enabled")));
    public static final float CRAFTING_BALANCE_ADDED = Float.parseFloat(String.valueOf(configFile.get("CraftingMoney").get("balanceAdded")));
    public static final HashMap<String, ?> SPECIFIC_CRAFTING_ITEMS =  configFile.get("CraftingMoney").get("specificItems");


    public static final boolean FARMING_MONEY_ENABLED = Boolean.parseBoolean( String.valueOf(configFile.get("FarmingMoney").get("enabled")));
    public static final float FARMING_BALANCE_ADDED = Float.parseFloat(String.valueOf(configFile.get("FarmingMoney").get("balanceAdded")));
    public static final HashMap<String, ?> SPECIFIC_CROPS =  configFile.get("FarmingMoney.specificCrops");


    public static final boolean ON_PLAYER_DEATH_ENABLED = Boolean.parseBoolean( String.valueOf(configFile.get("OnPlayerDeath").get("enabled")));
    public static final float ON_PLAYER_DEATH_AMOUNT = Float.parseFloat(String.valueOf(configFile.get("OnPlayerDeath").get("amount")));


}
