package tests;

import io.qameta.allure.Attachment;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
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
import utils.ExcelReader;
import utils.SeleniumDriverLogger;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.io.IOException;

@RunWith(value = Parameterized.class)
public class ExcelDataDrivenTest extends TestWatcher {
    WebDriver driver;
    EventFiringWebDriver e_driver;
    SeleniumDriverLogger eventListener;
    WebDriverWait wait;
    private String appURL;
    int waitTime = 10;

    public ExcelDataDrivenTest(String appURL) {
        this.appURL = appURL;
    }

    @Parameters
    public static Collection<Object[]> spreadsheetData() throws IOException, InvalidFormatException {
        // return new ExcelReader(new FileInputStream("src/main/resources/testData.xls")).getData();
        InputStream spreadsheet = new FileInputStream("src/main/resources/testData.xlsx");
        return new ExcelReader(spreadsheet).getData();
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
        System.out.println("***** Executing Excel Data Driven Test ***** ");
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

