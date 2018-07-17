package tests;

import objectRepository.FirstRepository;
import io.qameta.allure.Attachment;
import org.junit.*;
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

import java.io.IOException;

import static objectRepository.FirstRepository.Page1.*;


public class RepositoryBasedTest extends TestWatcher {
    WebDriver driver;
    EventFiringWebDriver e_driver;
    SeleniumDriverLogger eventListener;
    WebDriverWait wait;
    private String appURL = "http://www.google.com";
    int waitTime = 10;

    @Before()
    public void setUp() throws Exception {

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
    public void testEventsONE() throws IOException {
        System.out.println("***** Executing Test ONE ***** ");
        e_driver.get(appURL);
        takeScreenShot();
        FirstRepository.Page1.getSearchTextBox(e_driver).sendKeys(searchKeyword);
        System.out.println("'" + searchKeyword + "' " + " is sent to : " + FirstRepository.Page1.getSearchTextBox(e_driver));
        FirstRepository.Page1.getSearchTextBox(e_driver).submit();
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
