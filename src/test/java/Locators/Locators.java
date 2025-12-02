package Locators;

import Base.BaseFunction;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Locators extends BaseFunction {

    private static Page page;
    private static Playwright playwright;
    private static Browser browser;

    @BeforeTest
    public static void setup() throws InterruptedException {
        // Set up your page object here
        page = launchMaxDefaultBrowser("https://playwright.dev/java/docs/locators#locate-by-role");
    }

    @AfterTest
    public static void teardown() {
        tearDown(page);
    }

    /**
     * <a href="https://playwright.dev/java/docs/other-locators#css-locator">...</a>
     * <p>
     * TODO - Try different locators and combination from the above link
     */

    @Test
    public void css_Selector() {
        page.navigate("https://gmail.com");
        page.locator("[aria-label=\"Email or phone\"]").fill("gnana");
        page.click("button:has-text('Next')");
    }

    @Test
    void getByRoleLocators() {
        page.navigate("https://gmail.com");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Next"))
                .click();
    }

    @Test
    void hover_Locator() {
        page.navigate("https://playwright.dev/java/docs/locators#locate-by-role");
        Locator loc = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in"));
        loc.hover();
        loc.click();
    }

    /*
    <h3>Sign up</h3>
    <label>
    <input type="checkbox" /> Subscribe
    </label>
    <br/>
    <button>Submit</button>
     */
    @Test
    void locatorElementByImplicitRole() throws InterruptedException {
        page.navigate("https://playwright.dev/java/docs/locators#locate-by-role");
        Thread.sleep(3000);
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Sign up")))
                .isVisible();

        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Subscribe"))
                .check();
        Thread.sleep(3000);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(
                Pattern.compile("submit", Pattern.CASE_INSENSITIVE)))
                .click();
        System.out.println("Form Submitted Successfully");
    }

    //Locate By Labels  - <label>Password <input type="password" /></label>
    @Test
    void locateByLabels() throws InterruptedException {
        page.locator("//input[@type='password']").scrollIntoViewIfNeeded();
        assertThat(page.getByLabel("Password")).isVisible();
        page.getByLabel("Password").click();
        //page.getByLabel("Password").type("dummyText");
        page.getByLabel("Password").fill("Gnana@123");
        Thread.sleep(3000);
    }

    //Locate By PlaceHolder - <input type="email" placeholder="name@example.com" />
    @Test
    void locateByPlaceHolder() throws InterruptedException {
        page.getByPlaceholder("name@example.com").click();
        page.getByPlaceholder("name@example.com").isVisible();
        page.getByPlaceholder("name@example.com").fill("playwright@microsoft.com");
        Thread.sleep(5000);
    }
}
