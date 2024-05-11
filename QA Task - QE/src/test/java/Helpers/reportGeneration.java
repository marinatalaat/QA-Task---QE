package Helpers;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class reportGeneration {

    private static String testName;

    public static ExtentReports reportSetup(String testCaseName){
        ExtentReports extentReport = new ExtentReports();
        ExtentHtmlReporter report = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + testCaseName + ".html");
        report.config().setReportName("Test Automation detailed report");
        report.config().setTheme(Theme.DARK);
        extentReport.attachReporter(report);
        testName = testCaseName;
       return extentReport;
    }

    public static void openReportINDefaultBrowser() throws IOException {
        File file = new File(System.getProperty("user.dir")+"/Reports/"+testName+".html");
        Desktop.getDesktop().browse(file.toURI());
    }

    public static ExtentTest setTestStatus(ITestResult result, ExtentTest test) {
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, " Test is Failed");
        }
        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, " Test is Passed");
        }
        if(result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, " Test is Skipped");
        }
        return test;
    }
}
