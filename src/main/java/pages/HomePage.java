package main.java.pages;

import java.util.List;
import main.java.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    By addButton = By.xpath("//button[normalize-space()='Add']");

    //Leaves list
    By tableRows = By.cssSelector("tr.ng-star-inserted");
    By emptyLeaveList = By.cssSelector("tr.empty.fullwidth.ng-star-inserted");

    //confirmation alert
    By yesButton = By.cssSelector("ng-component.ng-star-inserted button.btn-primary");

    public HomePage(WebDriver driver) {
        super.driver = driver;
    }

    public AddPersonalLeavePage openAddPersonalLeavePopUp() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBeClickable(addButton, driver);
        click(addButton);
        return new AddPersonalLeavePage(driver);
    }

    public String getFromDateByIndex(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        return tableRowsEl.get(index).findElements(By.cssSelector("td")).get(0).getText();
    }

    public String getToDateByIndex(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        return tableRowsEl.get(index).findElements(By.cssSelector("td")).get(1).getText();
    }

    public String getDaysByIndex(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        return tableRowsEl.get(index).findElements(By.cssSelector("td")).get(2).getText();
    }

    public String getDaysOffByIndex(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        return tableRowsEl.get(index).findElements(By.cssSelector("td")).get(3).getText();
    }

    public String getStatusByIndex(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        return tableRowsEl.get(index).findElements(By.cssSelector("td")).get(4).getText();
    }

    public AddPersonalLeavePage editLeaveRecord(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        tableRowsEl.get(index).findElements(By.cssSelector("td")).get(5).findElements(By.cssSelector("button")).get(0);
        return new AddPersonalLeavePage(driver);
    }

    public void requestLeave(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        tableRowsEl.get(index).findElements(By.cssSelector("td")).get(5).findElements(By.cssSelector("button")).get(1);
        //is there a page to return?
    }

    public void deleteLeaveRecord(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        tableRowsEl.get(index).findElements(By.cssSelector("td")).get(5)
                .findElement(By.cssSelector("button.btn-default.ng-star-inserted"));
    }

    public boolean isLeavesListEmpty() {
        return driver.findElement(emptyLeaveList).isDisplayed();
    }

    public void confirmAlert() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBeClickable(yesButton, driver);
        click(yesButton);
    }
}
