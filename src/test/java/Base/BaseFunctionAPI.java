package Base;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class BaseFunctionAPI {

    private static final Playwright playwright = Playwright.create();

    public static Locator.ClickOptions setClickTimeout = new Locator.ClickOptions().setTimeout(5000);

    public static APIRequestContext requestContext() {
        return playwright.request().newContext();
    }

    public static APIResponse GETRequest(String URL) {
        try {
            return requestContext()
                    .get(URL);
        } finally {
            playwright.close();
        }
    }

    public  static APIResponse POSTRequest(String URL, Object data) {
        try {
            return requestContext()
                    .post(URL, RequestOptions.create().setData(data));
        } finally {
            playwright.close();
        }
    }

    public static void tearDown(){
        //close the playwright object
        playwright.close();
    }
}
