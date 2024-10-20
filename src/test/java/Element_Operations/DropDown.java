package Element_Operations;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DropDown {

    private static Page page;
    private static Playwright playwright;

    @BeforeAll
    public static void setup() {
        // Set up your page object here
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
    }

    @AfterAll
    public static void teardown() {
        // Close browser and playwright after all tests
        page.close();
        playwright.close();
    }

    @Test
    void dropDown_Select() {

        page.navigate("https://www.wikipedia.org/");

        //Select dropdown option by using value
        page.selectOption("select", "hi");

        //Select dropdown option by using label
        page.selectOption("select", new SelectOption().setLabel("Eesti"));

        //Select dropdown option by using index
        page.selectOption("select", new SelectOption().setIndex(1));
    }

    @Test
    void dropDown_Select_Options(){

        page.navigate("https://www.wikipedia.org/");

        //collect locators and options under select locator
        Locator values = page.locator("select > option");

        for (int i = 0; i < values.count(); i++) {
            System.out.println(values.nth(i).getAttribute("value"));
            System.out.println(values.nth(i).innerText());
            System.out.println("--------------------");
        }

        //similar action with ElementHandler
        List<ElementHandle> list = page.querySelectorAll("select > option");
    }
}
