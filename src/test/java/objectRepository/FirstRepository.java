package objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class FirstRepository extends RepositoryBase {

    public static class Page1 {
        public static String searchKeyword = "dorukcoskun.com";

        public static WebElement getSearchTextBox(EventFiringWebDriver driver) {
            WebElement element = null;
            element = findElementByClass(driver, "gsfi");
            return element;
        }
    }
}
