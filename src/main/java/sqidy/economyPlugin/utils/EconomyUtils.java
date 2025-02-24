package sqidy.economyPlugin.utils;

public class EconomyUtils {
    static public String configDir = "./plugins/EconomyPlugin/config.yml";
    static public String accountsDir = "./plugins/EconomyPlugin/accounts.yml";

    public static boolean isFloat(String str){
        try {
            Float.parseFloat(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
