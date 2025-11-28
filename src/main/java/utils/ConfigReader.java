package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties props;

    static {
        props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/resources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
