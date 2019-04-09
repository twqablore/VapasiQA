package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    Properties properties = new Properties();
    InputStream inputStream = null;

    public ConfigReader()  {
        loadProperties();
    }

    private void loadProperties() {

        try {
            inputStream = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
