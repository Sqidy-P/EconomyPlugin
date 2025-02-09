package sqidy.economyPlugin.handlers;

import org.bukkit.Bukkit;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileHandler {
    //region Variables
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



    //region Loading and appending data
    public static HashMap<String, HashMap<String, String>> loadDataFromAccounts(String filePath){
        //region Returns the info in yml file.
        Yaml yaml = new Yaml();

        try (FileInputStream inputStream = new FileInputStream(filePath)){
            return yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //endregion
    }

    public static void appendNewAccount(String uuid, String playerName, String balance, String filePath) {
        //region Formats player data
        HashMap<String, HashMap<String, String>> data = new HashMap<>();

        HashMap<String, String> playerData = new HashMap<>();
        playerData.put("player", playerName);
        playerData.put("balance", balance);

        data.put(uuid, playerData);
        //endregion

        //region Appends it to accounts.yml
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(options);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //endregion
    }
    //endregion
}
