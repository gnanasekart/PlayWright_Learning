package Element_Operations;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Checkbox  {
    private static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static final String URL = "http://tizag.com/htmlT/htmlcheckboxes.php";

    @BeforeTest
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

    @AfterTest
    public static void teardown()  {
        // Close browser and playwright after all tests

        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    void testBlockCheckBox()throws InterruptedException{
        page.navigate(URL);

        // Find and click checkbox
        //Locator block = page.locator("//h2[text()='HTML Checkbox Form:']/following-sibling::div[1]");
        //Locator block = page.locator("html>body>table:nth-of-type(3)>tbody>tr>td:nth-of-type(2)>table>tbody>tr>td>div:nth-of-type(4)");
        Locator checkboxes = page.locator("html>body>table:nth-of-type(3)>tbody>tr>td:nth-of-type(2)>table>tbody>tr>td>div:nth-of-type(4)>[type='checkbox']");

        for (int i = 1; i < checkboxes.count(); i++) {
            System.out.println(checkboxes.nth(i).getAttribute("value"));
            checkboxes.nth(i).click();
            Thread.sleep(2000);
        }

    }
}
