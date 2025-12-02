package Base;

import com.microsoft.playwright.*;

import java.awt.*;

public class BaseFunction {

    private static final Playwright playwright = Playwright.create();
    //Screen dimension
    public static final double HEIGHT = screenWidth().getHeight();
    public static final double WIDTH = screenWidth().getWidth();

    public static Locator.ClickOptions setClickTimeout = new Locator.ClickOptions().setTimeout(5000);


    /**
     * @purpose Launch the default browser in headless mode
     * @param URL String
     */
    public static void defaultBrowserLaunchHeadLess(String URL) {
        try (Playwright pw = Playwright.create()) {

            //Set browser as headless = false
            BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(true);
            //new chromium browser
            Browser browser = pw.chromium().launch(headedBrowser);
            //new page inside the browser
            Page page = browser.newPage();
            //new navigating page
            page.navigate(URL);
            //printing page title
            System.out.println(page.title());
        }
    }

    /**
     * @purpose Launch the default Chromium browser in headed mode
     * @param URL String
     */
    public static void defaultBrowserLaunch(String URL) {
        try (Playwright pw = Playwright.create()) {

            //Set browser as headless = false
            BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(false);
            //new chromium browser
            Browser browser = pw.chromium().launch(headedBrowser);
            //new page inside the browser
            Page page = browser.newPage();
            //new navigating page
            page.navigate(URL);
            //printing page title
            System.out.println(page.title());
        }
    }

    public static Page launchMaxDefaultBrowser(String URL) throws InterruptedException {
        // Use the shared playwright instance and keep browser/context alive until teardown
        BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = playwright.chromium().launch(headedBrowser);

        BrowserContext browserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize((int) WIDTH, (int) HEIGHT));

        Page page = browserContext.newPage();
        page.navigate(URL);
        System.out.println(page.title());
        Thread.sleep(1000);
        return page;
    }

    /**
     * @purpose Launch the specific browser in headed mode
     * @param browserName String
     * @param URL String
     */
    public static void launchBrowser(String browserName, String URL) {
        try (Playwright pw = Playwright.create()) {

            //Set browser as headless = false
            BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions()
                    .setChannel(browserName)
                    .setHeadless(false);
            //new chromium browser
            Browser browser = pw.chromium().launch(headedBrowser);
            //new page inside the browser
            Page page = browser.newPage();
            //new navigating page
            page.navigate(URL);
            //printing page title
            System.out.println(page.title());
        }
    }

    /**
     * @purpose Launch the specific browser in headed mode with Maximized window
     * @param   browserName String
     * @param   URL String
     * @return  Page Object that can used for test automation
     * NOTE: Remember to call tearDown(page) method to close the browser and playwright instance
     */

    public static Page launchBrowserWithMaximize(String browserName, String URL) {
            //Set browser as headless = false
            BrowserType.LaunchOptions headedBrowser = new BrowserType.LaunchOptions()
                    .setChannel(browserName)
                    .setHeadless(false);

            //new chromium browser
            Browser browser = playwright.chromium().launch(headedBrowser);

            //maximizing the browser window
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize((int) WIDTH, (int) HEIGHT));

            //new page inside the browser
            Page page = browserContext.newPage();

            //new navigating page
            page.navigate(URL);

            //printing page title
            System.out.println(page.title());
            return page;
    }

    public static void executePage(BrowserContext browserContext) throws InterruptedException {
        //new page inside the browser
        Page page = browserContext.newPage();

        //new navigating page
        page.navigate("https://google.co.in");

        //printing page title
        System.out.println(page.title());
        Thread.sleep(4000);
        tearDown(page);
    }

    public static void executePage(Browser browser) throws InterruptedException {
        //new page inside the browser
        Page page = browser.newPage();

        //new navigating page
        page.navigate("https://google.co.in");

        //printing page title
        System.out.println(page.title());
        Thread.sleep(4000);
        tearDown(page);
    }

    public static Dimension screenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static void tearDown(Page page){
        //close the web page
        page.close();
        //close the playwright object
        playwright.close();
    }
}
