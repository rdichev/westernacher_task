package main.java.manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.enumeration.DriverType;
import main.java.utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {

    public static WebDriver createWebDriver() {
        DriverType driverType = new ConfigFileReader().getDriverType();
        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case INTERNET_EXPLORER:
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            default:
                throw new IllegalStateException("Error: " + driverType);
        }
    }
}
