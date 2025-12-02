package UseCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.*;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Navigation {


    private static Page page;
    private static Browser browser;

    @BeforeTest
    public static void launch(){
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new LaunchOptions().setHeadless(true));
    }

    @Test
    public void navigate() throws InterruptedException {
        Page page = browser.newPage();

        page.navigate("https://google.co.in");
        page.navigate("https://gmail.com");

        page.goBack(new Page.GoBackOptions().setTimeout(2000));
        Thread.sleep(1000);

        page.goForward(new Page.GoForwardOptions().setTimeout(2000));
        Thread.sleep(500);
        page.reload();
    }
}
