package UseCase;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class windowsMaximize {

    //Screen dimension
    private static final double height = screenWidth().getHeight();
    private static final double width = screenWidth().getWidth();

    private static final Playwright playwright = Playwright.create();
    private static Path chromePath = Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
    private static final Path localChromePath = Paths.get("C:\\Users\\Admin\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
    private static Path ffPath = Paths.get("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    private static final Path edgePath = Paths.get("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");



    @Test
    public void maximize_Window_Toolkit() throws InterruptedException {
        System.out.println(height + "----" + width);

        /*
         *
         * When defining a new browser type in Playwright, it's essential to use the BrowserType.
         * LaunchOptions interface to set configuration options for the browser instance.
         * In the code snippet new BrowserType.LaunchOptions().setHeadless(false), the BrowserType.
         * LaunchOptions is used to create a new instance of the LaunchOptions class, which sets properties for the browser launch.
         * By setting headless to false, the browser will launch in a non-headless mode, allowing you to interact with it visually.
         * This approach provides more flexibility and control when creating new browser instances in Playwright.
         */

        LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(false);

        //new chromium browser
        Browser browser = playwright.chromium().launch(headedBrowser);

        //maximizing the browser window
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize((int) width, (int) height));

        executePage(browserContext);
    }
    //https://peter.sh/experiments/chromium-command-line-switches/

    @Test
    public void window_start_Maximized() throws InterruptedException {
        ArrayList<String> argument = new ArrayList<>();
        argument.add("--start-maximized");

        //Set channel and arguments
        LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setArgs(argument);

        //new chromium browser
        Browser browser = playwright.chromium().launch(headedBrowser);

        //maximizing the browser window
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

        executePage(browserContext);
    }

    @Test
    public void set_Executable_Path() throws InterruptedException {
        ArrayList<String> argument = new ArrayList<>();
        argument.add("--start-maximized");

        //Set channel and arguments
        LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(false).setExecutablePath(edgePath);

        //new chromium browser
        Browser browser = playwright.chromium().launch(headedBrowser);

        //maximizing the browser window
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

        executePage(browserContext);
    }


    @Test
    public void nonIncognito_Browser() throws InterruptedException {
        //Set browser as headless = false
        BrowserType.LaunchPersistentContextOptions headedBrowser = new BrowserType.LaunchPersistentContextOptions().setHeadless(false);

        //use chrome history from app local data by providing the location path, if empty means does not load local data
        BrowserContext browser = playwright.chromium().launchPersistentContext(Paths.get(localChromePath.toUri()), headedBrowser);

        executePage(browser);
    }

    public static Dimension screenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static void executePage(BrowserContext browserContext) throws InterruptedException {
        //new page inside the browser
        Page page = browserContext.newPage();

        //new navigating page
        page.navigate("https://google.co.in");

        //printing page title
        System.out.println(page.title());
        Thread.sleep(10000);
        tearDown(page);
    }

    public static void tearDown(Page page){
        //close the web page
        page.close();
        //close the playwright object
        playwright.close();
    }
}


