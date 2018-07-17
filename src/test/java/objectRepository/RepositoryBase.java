package objectRepository;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RepositoryBase {

    private static WebDriverWait wait = null;
    public static int waitTime = 2;
    final static Logger logger = Logger.getLogger(RepositoryBase.class);

    public static WebElement findElementById(WebDriver driver, String Id) {
        WebElement element = null;
        element = driver.findElement(By.id(Id));
        return element;
    }

    public static WebElement findElementByClass(WebDriver driver, String className) {
        WebElement element = null;
        element = driver.findElement(By.className(className));
        return element;
    }

    public static WebElement findElementByName(WebDriver driver, String name) {
        WebElement element = null;
        element = driver.findElement(By.name(name));
        return element;
    }

    public static WebElement findElementByXpath(WebDriver driver, String xPath) {
        WebElement element = null;
        element = driver.findElement(By.xpath(xPath));
        return element;
    }

    public static List<WebElement> findElementsByClass(WebDriver driver, String className) {
        List<WebElement> elements = null;
        elements = driver.findElements(By.className(className));
        return elements;
    }

    public static List<WebElement> findElementsByName(WebDriver driver, String name) {
        List<WebElement> elements = null;
        elements = driver.findElements(By.className(name));
        return elements;
    }

    public static List<WebElement> findElementsByXpath(WebDriver driver, String xPath) {
        List<WebElement> elements = null;
        elements = driver.findElements(By.xpath(xPath));
        return elements;
    }

}
