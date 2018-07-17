package tests;

import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumDriverLogger;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class DataDrivenTest extends TestWatcher {
    WebDriver driver;
    EventFiringWebDriver e_driver;
    SeleniumDriverLogger eventListener;
    WebDriverWait wait;
    private String appURL;
    int waitTime = 10;

    public DataDrivenTest(String appURL) {
        this.appURL = appURL;
    }


    @Parameters
    public static Collection testData() {

        return Arrays.asList(
                "http://www.google.com", "http://www.mobven.com", "http://www.dorukcoskun.com");
    }

    @Before()
    public void setUp() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, waitTime);
        e_driver = new EventFiringWebDriver(driver);
        eventListener = new SeleniumDriverLogger();
        e_driver.register(eventListener);
        e_driver.manage().window().maximize();

    }

    @Override
    public Statement apply(Statement base, Description description) {
        return super.apply(base, description);
    }

    @Test
    public void dataDriventURLTest() {
        System.out.println("***** Executing Data Driven Test ***** ");
        e_driver.get(appURL);
        takeScreenShot();
    }

    @After()
    public void tearDown() {
        if (e_driver != null) {
            e_driver.quit();
        }
    }

    @Attachment
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    protected void failed(Throwable e, Description description) {

    }
}

