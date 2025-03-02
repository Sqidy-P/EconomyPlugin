package sqidy.economyPlugin.handlers;

import org.bukkit.Bukkit;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;

import static sqidy.economyPlugin.utils.EconomyUtils.accountsDir;

public class FileHandler {
    public static HashMap<String, HashMap<String, String>> data;

    static File directory = new File("./plugins", "EconomyPlugin");
    static File config = new File("./plugins/EconomyPlugin", "config.yml");
    static File accounts = new File("./plugins/EconomyPlugin", "accounts.yml");

    public static void setup(){
        createDirectory();
        createFiles();

        loadDataFromYAML(accountsDir);
    }

    private static void createDirectory() {
        // Handles creation of EconomyPlugin Directory
        if (!directory.exists()) {
            if (directory.mkdir()) {
                Bukkit.getLogger().info("Successfully created EconomyPlugin's directory.");
            } else {
                Bukkit.getLogger().info("EconomyPlugin failed to make the data directory.");
            }
        } else {
            Bukkit.getLogger().info("The directory for the EconomyPlugin already exists.");
        }
    }

    private static void createFiles() {
        // Handles creation of empty config.yml
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

        // Handles creation of empty accounts.yml
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
    }

    
    // Loads YAML
    public static HashMap<String, HashMap<String, String>> loadDataFromYAML(String filePath){
        Yaml yaml = new Yaml();

        try (FileInputStream inputStream = new FileInputStream(filePath)){
            return yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Writes to accounts.yml
    public static void modifyAccountData(String uuid, String playerName, float balance, boolean appendAccount){
        if (!appendAccount){
            data = loadDataFromYAML(accountsDir);
        } else {
            data = new HashMap<>();
        }

        HashMap<String, String> playerData = new HashMap<>();

        playerData.put("player", playerName);
        playerData.put("balance", String.valueOf(balance));

        data.put(uuid, playerData);

        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(options);

        try(Writer writer = new FileWriter(accountsDir, appendAccount)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
