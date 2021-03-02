package main.java.pages;

import java.util.List;
import main.java.enumeration.LocalizationEnum;
import main.java.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private By addButton = By.xpath("//button[normalize-space()='Add']");

    //Leaves list
    private By tableRows = By.cssSelector("tr.ng-star-inserted");
    private By emptyLeaveList = By.cssSelector("tr.empty.fullwidth.ng-star-inserted");
    private By deleteButton = By.xpath("//button[text()='Delete']");
    private By exportButton = By.xpath("//button[text()='Export']");
    private By requestButton = By.xpath("//button[text()='Request']");

    //confirmation alert
    private By confirmationPopUp = By.cssSelector("div.cdk-overlay-pane");
    private By yesButton = By.cssSelector("ng-component.ng-star-inserted button.btn-primary");

    //alert
    private By alert = By.cssSelector("div.alert-success");

    //export
    private By exportDialog = By.cssSelector("div.cdk-overlay-pane");
    private By printButton = By.cssSelector("mat-dialog-actions button.btn-primary");
    private By cancelButton = By.cssSelector("mat-dialog-actions button:not(.btn-primary)");
    private By saveButton = By.xpath("//button[text()='Save']");

    //localization
    By localSpan = By.cssSelector("span.ng-tns-c2-0.ng-star-inserted");

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
        tableRowsEl.get(index).findElements(By.cssSelector("td"))
                .get(5).findElements(By.cssSelector("button")).get(0).click();
        return new AddPersonalLeavePage(driver);
    }

    public void requestLeave(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        tableRowsEl.get(index).findElements(By.cssSelector("td")).get(5).findElement(requestButton).click();

        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBePresent(By.cssSelector("td.accepted"), driver);
    }

    public void deleteLeaveRecord(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        tableRowsEl.get(index).findElements(By.cssSelector("td")).get(5).findElement(deleteButton).click();
    }

    public void exportLeaveRecord(int index) {
        List<WebElement> tableRowsEl = driver.findElements(tableRows);
        WebElement exportBtn = tableRowsEl.get(index).findElements(By.cssSelector("td")).get(5)
                .findElement(exportButton);
        exportBtn.click();
    }

    public boolean isLeavesListEmpty() {
        List<WebElement> elements = driver.findElements(emptyLeaveList);
        if (!elements.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void confirmDeletion() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBeVisible(confirmationPopUp, driver);
        waitUtils.waitForElementToBeClickable(yesButton, driver);
        click(yesButton);
        waitUtils.waitForInvisibilityOfElement(confirmationPopUp, driver);
    }

    public void waitUntilLeaveIsAdded() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBeVisible(tableRows, driver);
    }

    public void waitForSuccessAlertDisplayed() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBeVisible(alert, driver);
    }

    public String getSuccessAlertText() {
        return getText(alert);
    }

    public void waitForExportModalToBeDisplayed() {
        WaitUtils waitUtils = new WaitUtils();
        waitUtils.waitForElementToBeVisible(exportDialog, driver);
    }

    public void clickPrintButton() {
        click(printButton);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public boolean isLanguageSelected(LocalizationEnum localizationEnum) {
        if (getText(localSpan).contains(localizationEnum.getValue())) {
            return true;
        } else {
            return false;

        }
    }

    public void selectLanguage(LocalizationEnum localizationEnum) {
        if (!isLanguageSelected(localizationEnum)) {
           click(localSpan);
           click(By.xpath("//span[text()='" + localizationEnum.getValue() + "']"));
        }
    }

}
