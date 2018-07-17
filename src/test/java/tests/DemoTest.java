package tests;

import io.qameta.allure.Attachment;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumDriverLogger;

import java.io.IOException;

public class DemoTest extends TestWatcher {
    WebDriver driver;
    EventFiringWebDriver e_driver;
    SeleniumDriverLogger eventListener;
    WebDriverWait wait;
    private String appURL = "http://www.google.com";
    int waitTime = 10;
    private String errMessage = "The email and password you entered don't match.";

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
    }

    @Test
    public void testEventsTWO() {
        System.out.println("***** Executing Test Two ***** ");
        e_driver.get(appURL);
        e_driver.findElement(By.id("Email")).sendKeys("username");
        e_driver.findElement(By.id("next")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        e_driver.findElement(By.id("Passwd")).sendKeys("password");
        e_driver.findElement(By.id("signIn")).click();
        String pageHeaderText = e_driver.findElement(By.id("errormsg_0_Passwd")).getText();
        Assert.assertTrue(pageHeaderText.equalsIgnoreCase(errMessage));
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
