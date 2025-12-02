package Element_Operations;

import Base.BaseFunction;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HandleTabAndPopUps extends BaseFunction {

    private static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    private static final String URL = "https://sso.teachable.com/secure/673/identity/sign_up/otp";

    @BeforeTest
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
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
    }

    @AfterTest
    public static void teardown() {
        // Close browser and playwright after all tests
        page.close();
        browserContext.close();
        playwright.close();
    }

    @Test
    void handleAlertPopUp() {
        page.navigate(URL);
        Locator.ClickOptions setTimeout = new Locator.ClickOptions().setTimeout(3000);
        Page popup = page.waitForPopup(() -> {
            page.locator("text=Privacy").nth(1).click();
        });

        popup.locator("(//div[text()='Creator login'])[2]").click(setClickTimeout);
        //popup.locator("input[type=\"email\"]").fill("gnana");

        popup.close();
    }
}
