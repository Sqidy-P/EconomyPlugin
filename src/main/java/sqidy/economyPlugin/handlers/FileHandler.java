package sqidy.economyPlugin.handlers;

import org.bukkit.Bukkit;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;

public class FileHandler {
    //region Variables
    public static HashMap<String, HashMap<String, String>> data;

    static String configDir = "./plugins/EconomyPlugin/config.yml";
    static public String accountsDir = "./plugins/EconomyPlugin/accounts.yml";

    static File directory = new File("./plugins", "EconomyPlugin");
    static File config = new File("./plugins/EconomyPlugin", "config.yml");
    static File accounts = new File("./plugins/EconomyPlugin", "accounts.yml");
    //endregion

    //region Setup
    public static void setup(){
        createDirectory();
        createFiles();

        loadDataFromYAML(accountsDir);
    }

    private static void createDirectory() {
        //region Handles creation of EconomyPlugin Directory
        if (!directory.exists()) {
            if (directory.mkdir()) {
                Bukkit.getLogger().info("Successfully created EconomyPlugin's directory.");
            } else {
                Bukkit.getLogger().info("EconomyPlugin failed to make the data directory.");
            }
        } else {
            Bukkit.getLogger().info("The directory for the EconomyPlugin already exists.");
        }
        //endregion
    }

    private static void createFiles() {
        //region Handles creation of default config.yml
        if (!config.exists()) {
            try {
                if (config.createNewFile()) {
                    Bukkit.getLogger().info("Successfully created the default config.yml file.");
                }
            } catch (IOException e) {
                Bukkit.getLogger().info("Failed to create config.yml.");
            }
        } else {
            Bukkit.getLogger().info("config.yml already exists.");
        }
        //endregion

        //region Handles creation of empty accounts.yml
        if (!accounts.exists()) {
            try {
                if (accounts.createNewFile()) {
                    Bukkit.getLogger().info("Successfully created the default accounts.yml file.");
                }
            } catch (IOException e) {
                Bukkit.getLogger().info("Failed to create accounts.yml.");
            }
        } else {
            Bukkit.getLogger().info("accounts.yml already exists.");
        }
        //endregion
    }
    //endregion

    

    public static HashMap<String, HashMap<String, String>> loadDataFromYAML(String filePath){
        //region Returns the info in yml file.
        Yaml yaml = new Yaml();

        try (FileInputStream inputStream = new FileInputStream(filePath)){
            return yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //endregion
    }

    public static void modifyAccountData(String uuid, String playerName, String balance, boolean appendAccount){
        //region Check for append
        if (!appendAccount){
            data = loadDataFromYAML(accountsDir);
        } else {
            data = new HashMap<>();
        }
        //endregion

        //region The data of the UUID
        HashMap<String, String> playerData = new HashMap<>();
//        String playerName = Bukkit.getServer().getPlayer(uuid).getName();
//        String playerName = "Name Placeholder";

         playerData.put("player", playerName);
        playerData.put("balance", balance);

        data.put(uuid, playerData);
        //endregion

        //region Write it to accounts.yml
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(options);

        try(Writer writer = new FileWriter(accountsDir, appendAccount)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //endregion
    }

    public static void addToBalance(String uuid, String playerName, float amount){
        //region Get data, make sure it's only ~.xx, then modify it in accounts.yml
        HashMap<String, HashMap<String, String>> data = loadDataFromYAML(accountsDir);

        float currentBalance = Float.parseFloat(data.get(uuid).get("balance"));
        String newBalance = String.format("%.2f", (currentBalance + amount));

        modifyAccountData(uuid, playerName, newBalance, false);
        //endregion
    }

    public static String getBalance(String uuid){
        return FileHandler.loadDataFromYAML(accountsDir).get(uuid).get("balance");
    }
}
