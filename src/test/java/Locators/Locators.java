package Locators;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Locators {

    private static Page page;
    private static Playwright playwright;
    private static Browser browser;

    @BeforeAll
    public static void setup() {
        // Set up your page object here
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.navigate("https://gmail.com");
    }

    @AfterAll
    public static void teardown() {
        // Close browser and playwright after all tests
        page.close();
        playwright.close();
    }

    /**
     * <a href="https://playwright.dev/java/docs/other-locators#css-locator">...</a>
     * <p>
     * TODO - Try different locators and combination from the above link
     */

    @Test
    public void css_Selector() {
        page.locator("[aria-label=\"Email or phone\"]").fill("gnana");
        page.click("button:has-text('Next')");
    }

    @Test
    void getByRoleLocators() throws InterruptedException {
        page
                .getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                        .setName("Next"))
                .click();
        Thread.sleep(3000);
    }

    @Test
    void hover_Locator() throws InterruptedException {
        page.navigate("https://mail.rediff.com/cgi-bin/login.cgi/");
        page.getByText("Get a new Rediffmail ID").hover();
        Thread.sleep(3000);
    }
}
