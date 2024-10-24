package Element_Operations;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HandleFrame {

    private static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static final String URL = "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get";

    @BeforeAll
    public static void setup() {
        // Set up your page object here
        playwright = Playwright.create();
        ArrayList<String> argument = new ArrayList<>();
        argument.add("--start-maximized");

        //Set channel and arguments
        BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(argument);

        //new chromium browser
        Browser browser = playwright.chromium().launch(headedBrowser);

        //maximizing the browser window
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
    }

    @AfterAll
    public static void teardown()  {
        // Close browser and playwright after all tests
        page.close();
        //browser.close();
        playwright.close();
    }

    @Test
    void handleAlertPopUp() {
        page.navigate(URL);

        //first locating the frame locator
        page.frameLocator("[name='iframeResult']")

                //switch to the frame and identify the element inside the frame
                .locator("body > button")

                //clickOptions set a timeout of 2 seconds, and it will consider once after the page load completes not after page launch.
                .click(new Locator.ClickOptions().setTimeout(2000));
    }
}
