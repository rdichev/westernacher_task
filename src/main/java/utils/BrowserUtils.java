package main.java.utils;

import java.util.Set;
import org.openqa.selenium.WebDriver;

public class BrowserUtils {

    public static void switchToBrowserTab(WebDriver driver) {
        Set<String> windowHandlers = driver.getWindowHandles();
        for (String handler: windowHandlers) {
            if (handler != driver.getWindowHandle()) {
                driver.switchTo().window(handler);
            }
        }
    }
}
