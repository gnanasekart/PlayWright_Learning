package UseCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Navigation {


    private static Playwright playwright;
    private static Page page;
    private static Browser browser;

    @BeforeAll
    public static void launch(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
    }

    @Test
    public void navigate() throws InterruptedException {

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
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
