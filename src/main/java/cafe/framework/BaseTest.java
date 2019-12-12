package cafe.framework;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import java.util.concurrent.TimeUnit;


@Listeners({BaseTest.ScreenshotListener.class})
public class BaseTest {
    protected static Settings settings = new Settings();
    private static String testName;
    public static String time = new SimpleDateFormat("yyyy.MM.dd 'at' HHmm ss z").format(Calendar.getInstance().getTime());
    private static String filepath = settings.getOutputDir() + time + "/";
    public static ExtentReports reports;
    public static ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public static void beforeSuite(Method method) {
        testName = method.getName();
        test = reports.startTest(testName, testName);
        test.log(LogStatus.INFO, testName + " is starting...");
        BasePage.driver = settings.getDriver();
        BasePage.settings = settings;
        BasePage.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.driver.manage().window().maximize();
        BasePage.driver.get(settings.getBaseUrl());
    }


    @AfterMethod(alwaysRun = true)
    public static void after() {
        BasePage.driver.quit();
    }

    @BeforeClass
    public synchronized void initialize() {
        reports = new ExtentReports(filepath + "/HtmlReport/index.html", true);
    }

    @AfterMethod
    public void tearDown() {
        reports.endTest(test);
        reports.flush();
    }

    public static class ScreenshotListener extends TestListenerAdapter {

        @Override
        public void onTestFailure(ITestResult result) {
            test.log(LogStatus.FAIL, "<span class='label failure'>" + result.getName() + "</span>",
                    "<pre>Error message : " + "\n" + result.getThrowable().getMessage() + "</pre>");
            String screenShotPath = filepath + result.getMethod().getMethodName() + ".png";
            File screenshot1 = new File(screenShotPath);
            File screenshotTempFile = ((TakesScreenshot) BasePage.driver)
                    .getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshotTempFile, screenshot1);
            } catch (IOException e) {
                test.log(LogStatus.INFO, e.getMessage());
            }
            test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture(screenshot1.getAbsolutePath()));
        }
    }
}
