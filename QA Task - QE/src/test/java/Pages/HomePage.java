package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static Helpers.Utils.ClickOnElement;
import static Helpers.Utils.waitVisibilityOfElement;

public class HomePage {

    // By Locators and driver
    private WebDriver driver;
    private WebDriverWait wait;
    private By registerLink = By.className("ico-register");
    private By myAccountLink = By.className("ico-account");
    private By logOutLink = By.className("ico-logout");


    // Constructor
    public HomePage(WebDriver driver) {
        this.driver= driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public HomePage navigateToHomepage(String URL){
        driver.navigate().to(URL);
        waitVisibilityOfElement(wait, registerLink);
        return this;
    }

    public RegisterPage ClickOnRegisterLink(){
        ClickOnElement(driver, wait, registerLink);
        return new RegisterPage(driver);
    }


    public void AssertOnAccountCreated(){
        waitVisibilityOfElement(wait, myAccountLink);

        Assert.assertTrue(driver.findElement(myAccountLink).isDisplayed());
        Assert.assertTrue(driver.findElement(logOutLink).isDisplayed());
    }

}
