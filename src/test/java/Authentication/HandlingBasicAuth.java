package Authentication;

import Base.BaseFunction;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class HandlingBasicAuth extends BaseFunction {

    @Test
    void basicAuthHandling() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setHttpCredentials("admin", "admin")
                .setViewportSize((int) WIDTH, (int) HEIGHT)
        );

        Page page = browserContext.newPage();
        page.navigate("https://the-internet.herokuapp.com/basic_auth");


        tearDown(page);
    }
}
