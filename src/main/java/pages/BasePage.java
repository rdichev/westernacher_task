package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public void clearText(By by) {
        WebElement field = driver.findElement(by);
        field.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.textToBe(by, ""));
    }

}
