package helpers;

import java.io.*;
import java.util.Properties;

public class PropertiesHelper {
    private static final String FILE_PATH = "src/main/resources/config.properties";
    private final String url;
    private final String user;
    private final String password;
    private final Properties properties;

    private static PropertiesHelper propertiesHelper;
    static {
        try {
            propertiesHelper = new PropertiesHelper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PropertiesHelper() throws IOException {
        this.properties = new Properties();
        FileReader fileReader = new FileReader(FILE_PATH);
        this.properties.load(fileReader);
        this.url = this.properties.getProperty("url");
        this.user = this.properties.getProperty("username");
        this.password = this.properties.getProperty("password");
    }

    public static PropertiesHelper getInstance() throws IOException {
        return propertiesHelper;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}