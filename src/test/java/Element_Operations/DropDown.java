package Element_Operations;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class DropDown {

    private static Page page;
    private static Playwright playwright;

    @BeforeTest
    public static void setup() {
        // Set up your page object here
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.navigate("https://www.wikipedia.org/");
    }

    @AfterTest
    public static void teardown() {
        // Close browser and playwright after all tests
        page.close();
        playwright.close();
    }

    @Test
    void dropDown_Select() {
        //Select dropdown option by using value
        page.selectOption("select", "hi");

        //Select dropdown option by using label
        page.selectOption("select", new SelectOption().setLabel("Eesti"));

        //Select dropdown option by using index
        page.selectOption("select", new SelectOption().setIndex(1));
    }

    @Test
    void dropDown_Select_Options(){
        //collect locators and options under select locator
        Locator values = page.locator("select > option");

        for (int i = 0; i < values.count(); i++) {
            System.out.println(values.nth(i).innerText()+"-----"+values.nth(i).getAttribute("value"));
        }
    }

    @Test
    void dropDown_ElementHandle(){
        //similar action with ElementHandler
        List<ElementHandle> list = page.querySelectorAll("select > option");

        System.out.println(list.size());

        for (ElementHandle e : list) {
            System.out.println(e.innerText()+"------------"+e.getAttribute("lang"));
        }
    }

    @Test
    void printLinks() {
        // Print all links on the page
        //Locator links = page.locator("a");
        Locator links = page.locator("//*[@id=\"www-wikipedia-org\"]/footer/nav/div/a");
        System.out.println(links.count());
        for (int i = 0; i < links.count(); i++) {

            System.out.println(links.nth(i).innerText() + " ----- " + links.nth(i).getAttribute("href"));
        }
    }
}
