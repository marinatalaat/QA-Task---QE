package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static Helpers.Utils.waitVisibilityOfElement;

public class RegisterPage {

    // By Locators and driver
    private WebDriver driver;
    private WebDriverWait wait;
    private By registerPageTitle = By.className("page-title");
    private By maleGenderType = By.id("gender-male");
    private By femaleGenderType = By.id("gender-female");
    private By firstNameField = By.xpath("//input[@id='FirstName']");
    private By lastNameField = By.xpath("//input[@id='LastName']");
    private By dayField = By.xpath("//select[@name='DateOfBirthDay']");
    private By monthField = By.xpath("//select[@name='DateOfBirthMonth']");
    private By yearField = By.xpath("//select[@name='DateOfBirthYear']");
    private By emailField = By.xpath("//input[@id='Email']");
    private By passwordField = By.xpath("//input[@id='Password']");
    private By confirmPasswordField = By.xpath("//input[@id='ConfirmPassword']");
    private By registerButton = By.id("register-button");
    private By registerConfirmationMessage = By.xpath("//div[@class='result']");
    private By continueButton = By.xpath("//div[@class='buttons']//a");



    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver= driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public RegisterPage fillFormWithValidData(char genderType, String firstName, String lastName, String day, String month, String year, String email, String password){
        waitVisibilityOfElement(wait, registerPageTitle);

        if (genderType == 'M' | genderType == 'm'){
            driver.findElement(maleGenderType).click();
        }
        if (genderType == 'F' | genderType == 'f'){
            driver.findElement(femaleGenderType).click();
        }

        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);

        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);

        driver.findElement(dayField).sendKeys(day);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);


        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(password);

        return this;
    }

    public RegisterPage ClickRegisterButton (String confirmationMessage){
        waitVisibilityOfElement(wait, registerButton);
        driver.findElement(registerButton).click();

        Assert.assertEquals(driver.findElement(registerConfirmationMessage).getText(), confirmationMessage);
        return this;
    }

    public HomePage clickOnContinueButton(){
        driver.findElement(continueButton).click();

        return new HomePage(driver);
    }
}
