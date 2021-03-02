package main.java.pages;

import main.java.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailAddressField = By.id("emailAddress");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.id("logButton");

    public LoginPage(WebDriver driver) {
        super.driver = driver;
    }

    public void enterEmailAddress(String emailAddress) {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBePresent(emailAddressField, driver);
        enterText(emailAddressField, emailAddress);
    }

    public void enterPassword(String password) {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBePresent(passwordField, driver);
        enterText(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public HomePage login(String emailAddress, String password) {
        enterEmailAddress(emailAddress);
        enterPassword(password);
        clickLoginButton();
        return new HomePage(driver);
    }

}
