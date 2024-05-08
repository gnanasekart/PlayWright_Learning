package UseCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LaunchBrowser {


    public static void main(String[] args) {
        try (Playwright pw = Playwright.create()) {
            BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(false);
            Browser browser = pw.chromium().launch(headedBrowser);
            Page page = browser.newPage();
            page.navigate("https://google.co.in");
            System.out.println(page.title());
            page.close();
            pw.close();
        }
    }
}
