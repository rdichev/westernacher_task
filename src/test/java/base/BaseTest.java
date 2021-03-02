package base;

import java.util.concurrent.TimeUnit;
import main.java.enumeration.LocalizationEnum;
import main.java.manager.DriverManager;
import main.java.pages.HomePage;
import main.java.pages.LoginPage;
import main.java.utils.ConfigFileReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    private final ConfigFileReader configFileReader = new ConfigFileReader();
    public WebDriver driver;
    public HomePage homePage;

    @BeforeEach
    void setup() {
        driver = DriverManager.createWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitWait(), TimeUnit.SECONDS);
        driver.get(configFileReader.getApplicationUrl());
        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.login(configFileReader.getEmailAddress(), configFileReader.getPassword());

        homePage.selectLanguage(LocalizationEnum.ENGLISH);
    }

    @AfterEach
    void teardown() {
        driver.close();
        driver.quit();
    }
}
