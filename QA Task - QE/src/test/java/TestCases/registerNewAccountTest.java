package TestCases;

import Helpers.PropertiesLoader;
import Pages.HomePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

import static Helpers.Utils.takeScreenShot;
import static Helpers.reportGeneration.*;

public class registerNewAccountTest {

    String FilePath="ConfigData/Data.properties";
    String URL= PropertiesLoader.readPropertyFile(FilePath).getProperty("URL");
    public WebDriver driver;
    ExtentReports extentReport;
    ExtentTest test;


    @DataProvider(name = "new account details data")
    public Object[][] newAccountDetails(){
        return new Object[][]{
                {'M',"FirstName", "LastName", "20", "June", "1996", "emailText@format.com", "1234567", "Your registration completed"}
        };
    }
    
    @Test(dataProvider = "new account details data")
    public void registerNewAccountWithValidData(char genderType, String firstName, String lastName, String day, String month, String year, String email, String password, String confirmationMessage){
        new HomePage(driver).ClickOnRegisterLink().
                fillFormWithValidData(genderType, firstName, lastName, day, month, year, email, password)
                .ClickRegisterButton(confirmationMessage)
                .clickOnContinueButton()
                .AssertOnAccountCreated();
    }




    // Configurations
    @BeforeClass
    public void startDriver() {
        driver = new ChromeDriver();
        new HomePage(driver).navigateToHomepage(URL);

    }
    @BeforeMethod
    public void beforeMethod(Method method){
        extentReport = reportSetup(method.getName());
        test = extentReport.createTest(method.getName());
        System.out.println(method.getName()  +  "  method name");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE)
            takeScreenShot(driver, result);

        setTestStatus(result, test);
        extentReport.flush();
    }
    @AfterClass
    public void TearDown() throws IOException {
        driver.quit();
        openReportINDefaultBrowser();
    }
}
