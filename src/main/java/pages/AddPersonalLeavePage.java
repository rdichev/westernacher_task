package main.java.pages;

import main.java.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddPersonalLeavePage extends BasePage {

    By addPersonalLeave = By.cssSelector("div.cdk-overlay-pane");
    By addButton = By.cssSelector("div.cdk-overlay-pane button[class='btn btn-primary ng-star-inserted']");
    By cancelButton = By.cssSelector("");
    By fromInputField = By.id("fromDate");
    By toInputField = By.id("toDate");

    public AddPersonalLeavePage(WebDriver driver) {
        super.driver = driver;
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBePresent(addPersonalLeave, driver);
    }

    public boolean isAddPersonalLeaveDisplayed() {
        return driver.findElement(addPersonalLeave).isDisplayed();
    }

    public void waitUntilAddPersonalLeaveIsClosed() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForInvisibilityOfElement(addPersonalLeave, driver);
    }

    public void clickAddButton() {
        click(addButton);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }

    public void clearFromDateField() {
        clearText(fromInputField);
    }

    public void clearToDateField() {
        clearText(toInputField);
    }

    public void enterFromDate(String fromDate) {
        clearFromDateField();
        enterText(fromInputField, fromDate);
    }

    public void enterToDate(String toDate) {
        clearToDateField();
        enterText(toInputField, toDate);
    }

    public boolean isAddButtonDisabled() {
        return !driver.findElement(addButton).isEnabled();
    }
}
