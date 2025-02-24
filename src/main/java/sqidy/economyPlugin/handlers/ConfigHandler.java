package sqidy.economyPlugin.handlers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class ConfigHandler {
    public static HashMap<String, HashMap<String, HashMap<String, ?>>> loadConfig(String filePath){
        Yaml yaml = new Yaml();

        try (FileInputStream inputStream = new FileInputStream(filePath)){
            return yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
