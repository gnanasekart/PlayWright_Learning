package Base;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class BaseFunctionAPI {

    private static final Playwright playwright = Playwright.create();

    public static Locator.ClickOptions setClickTimeout = new Locator.ClickOptions().setTimeout(5000);

    public static APIResponse requestGet(String URL) {
        try {
            return playwright.request()
                    .newContext()
                    .get(URL);
        } finally {
            playwright.close();
        }
    }

    public static void tearDown(){
        //close the playwright object
        playwright.close();
    }
}
