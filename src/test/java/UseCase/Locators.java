package UseCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Locators {

    private static Page page;
    private static Browser browser;

    @BeforeAll
    public static void launch(){
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
    }

    /**
     * <a href="https://playwright.dev/java/docs/other-locators#css-locator">...</a>
     * <p>
     * TODO - Try different locators and combination from the above link
     */

    @Test
    public void css_Selector() {
        Page page = browser.newPage();

        page.navigate("https://gmail.com");

        page.locator("[aria-label=\"Email or phone\"]").fill("gnana");
        page.click("button:has-text('Next')");
    }
}
