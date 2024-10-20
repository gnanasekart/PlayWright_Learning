package Element_Operations;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Checkbox  {
    private static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static final String URL = "http://tizag.com/htmlT/htmlcheckboxes.php";

    @BeforeAll
    public static void setup() {
        // Set up your page object here
        playwright = Playwright.create();
        ArrayList<String> argument = new ArrayList<>();
        argument.add("--start-maximized");
        argument.add("--disable-adv");

        //Set channel and arguments
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(argument));
        page = browser.newPage();
    }

    @AfterAll
    public static void teardown()  {
        // Close browser and playwright after all tests

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    void testCheckBox()throws InterruptedException{
        page.navigate(URL);

        // Find and click checkbox
        Locator value = page.locator("//div[4]//input");

        for (int i = 1; i <= value.count(); i++) {
            System.out.println(value.nth(i).getAttribute("value"));
            value.nth(i).click();
            Thread.sleep(2000);
        }

    }
}
