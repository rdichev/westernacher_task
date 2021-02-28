package main.java.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import main.java.enumeration.DriverType;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "src/main/resources/configuration.properties";

    public ConfigFileReader() {
        try {
            FileReader fileReader = new FileReader(propertyFilePath);
            properties = new Properties();
            properties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public long getImplicitWait() {
        String implicitWait = getProperty("implicitWait");
        if (implicitWait == null) {
            throw new IllegalArgumentException("Invalid implicitWait value");
        }
        return Long.parseLong(implicitWait);
    }

    public long getDefaultWait() {
        String defaultWait = getProperty("defaultWait");
        if (defaultWait == null) {
            throw new IllegalArgumentException("Invalid defaultWait value");
        }
        return Long.parseLong(defaultWait);
    }

    public DriverType getDriverType() {
        String browserName;
        // if the browser is specified as a system property the test will use it
        // otherwise the browser specified in configuration.properties will be used
        String browserSystemProperty = System.getProperty("browser");
        if (browserSystemProperty == null) {
            browserName = properties.getProperty("browser");
        } else {
            browserName = browserSystemProperty;
        }

        switch (browserName) {
            case "chrome":
                return DriverType.CHROME;
            case "firefox":
                return DriverType.FIREFOX;
            case "iexplorer":
                return DriverType.INTERNET_EXPLORER;
            default:
                throw new IllegalArgumentException("Invalid browser value: " + browserName);
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url == null) {
            throw new IllegalArgumentException("url is not set.");
        }
        return url;
    }

    public String getLocal() {
        String local = properties.getProperty("localization");
        if (local == null) {
            throw new IllegalArgumentException("localization is not set.");
        }
        return local;
    }

    public String getEmailAddress() {
        String emailAddress = properties.getProperty("email");
        if (emailAddress == null) {
            throw new IllegalArgumentException("email address is not set.");
        }
        return emailAddress;
    }

    public String getPassword() {
        String pass = properties.getProperty("password");
        if (pass == null) {
            throw new IllegalArgumentException("password is not set.");
        }
        return pass;
    }
}
