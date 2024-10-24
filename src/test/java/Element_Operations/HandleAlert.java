package Element_Operations;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HandleAlert {

    private static Page page;
    private static Playwright playwright;
    private static Browser browser;
    private static final String URL = "https://mail.rediff.com/cgi-bin/login.cgi/";

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
    void handleAlertPopUp() {
        page.navigate(URL);

        //Enabling a listener to capture the alert message and accept/denied, etc.
        page.onDialog(alert -> {
            System.out.println("Alert message: " + alert.message());
            //alert.accept();
            alert.dismiss();

            Assertions.assertTrue(alert.message().contains("Please enter a valid user name"));
        });

        //Submitting the form
        page.locator("input[type=submit]").click();
    }
}
