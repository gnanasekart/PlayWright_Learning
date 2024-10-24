package Element_Operations;

import UseCase.WindowsMaximize;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HandleTabAndPopUps {

    private static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    private static final String URL = "https://sso.teachable.com/secure/673/identity/sign_up/otp";

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
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = browserContext.newPage();
    }

    @AfterAll
    public static void teardown() {
        // Close browser and playwright after all tests
        page.close();
        browserContext.close();
        playwright.close();
    }

    @Test
    void handleAlertPopUp() {
        page.navigate(URL);

        Page popup = page.waitForPopup(() -> {
            page.locator("text=Privacy").nth(1).click();
        });

        popup.locator("(//div[text()='Creator login'])[2]").click();
        //popup.locator("input[type=\"email\"]").fill("gnana");

        popup.close();
    }
}
