package sqidy.economyPlugin.handlers;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

public class FileHandler {
    //region Variables
    static public String configDir = "./plugins/EconomyPlugin/config.yml";
    static public String accountsDir = "./plugins/EconomyPlugin/accounts.yml";

    static File directory = new File("./plugins", "EconomyBukkit");
    static File config = new File("./plugins/EconomyBukkit", "config.yml");
    static File accounts = new File("./plugins/EconomyBukkit", "accounts.yml");
    //endregion
    
    private static void createDirectory() {
        //region Handles creation of EconomyBukkit Directory
        if (!directory.exists()) {
            if (directory.mkdir()) {
                Bukkit.getLogger().info("Successfully created EconomyBukkit's directory.");
            } else {
                Bukkit.getLogger().info("EconomyBukkit failed to make the data directory.");
            }
        } else {
            Bukkit.getLogger().info("The directory for the EconomyBukkit already exists.");
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

    public static void setup(){
        createDirectory();
        createFiles();
    }
}
