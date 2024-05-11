package Helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Utils {


    public static void ClickOnElement(WebDriver driver, WebDriverWait wait, By Element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
        driver.findElement(Element).click();
    }

    public static void waitVisibilityOfElement(WebDriverWait wait, By Element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
    }
    public static void takeScreenShot(WebDriver driver, ITestResult result) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/ScreenShots/"+result.getName()+".png"));
    }
}
