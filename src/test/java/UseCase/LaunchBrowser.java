package UseCase;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.*;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LaunchBrowser {


    public static void main(String[] args) {
        try (Playwright pw = Playwright.create()) {

            //Set browser as headless = false
            LaunchOptions headedBrowser = new LaunchOptions().setHeadless(false);
            //new chromium browser
            Browser browser = pw.chromium().launch(headedBrowser);
            //new page inside the browser
            Page page = browser.newPage();
            //new navigating page
            page.navigate("https://google.co.in");
            //printing page title
            System.out.println(page.title());
            //close the web page
            page.close();
            //close the playwright object
            pw.close();
        }
    }


}
