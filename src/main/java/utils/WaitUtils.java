package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    ConfigFileReader configFileReader = new ConfigFileReader();

    public void waitForElementToBeClickable(By by, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, configFileReader.getDefaultWait());
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBePresent(By by, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, configFileReader.getDefaultWait());
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForInvisibilityOfElement(By by, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, configFileReader.getDefaultWait());
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
    }

    public void waitForElementToBeVisible(By by, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, configFileReader.getDefaultWait());
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementText(By by, String text, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, configFileReader.getDefaultWait());
        wait.until(ExpectedConditions.textToBe(by, text));

    }

}
