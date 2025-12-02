package Handling_User_Gestures;

import Base.BaseFunction;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class HandlingShadowRootElements extends BaseFunction {

    @Test
    public void TestShadowRootElements1() {
        Page page = launchBrowserWithMaximize("chrome", "https://books-pwakit.appspot.com/explore?q=");

        page.locator("#input").fill("Java");
    }

    /*
    Shadow DOM element handling in selenium sample script
public void TestShadowRootElements2() {
    WebDriver driver = new ChromeDriver();
    driver.get("chrome://downloads/");
    driver.manage().window().maximize();

    try {
        // Get the shadow host element
        WebElement shadowHost = driver.findElement(By.tagName("downloads-manager"));

        // Execute script to access shadow DOM and find the search input
        WebElement searchInput = (WebElement) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].shadowRoot.querySelector('#searchInput')", shadowHost);

        // Interact with the shadow DOM element
        searchInput.sendKeys("Java");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        driver.quit();
    }
}

     */

    @Test
    public void TestShadowRootElements2() {
        Page page = launchBrowserWithMaximize("chrome", "chrome://downloads/");

        page.locator("#searchInput").fill("Java");
    }
}
