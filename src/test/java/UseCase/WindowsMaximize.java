package UseCase;

import Base.BaseFunction;
import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WindowsMaximize extends BaseFunction  {

    //Screen dimension
    public static final double HEIGHT = screenWidth().getHeight();
    public static final double WIDTH = screenWidth().getWidth();

    private static final Playwright playwright = Playwright.create();
    private static Path chromePath = Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
    private static final Path localChromePath = Paths.get("C:\\Users\\gnana.SEKARSUMITHRA\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
    private static Path ffPath = Paths.get("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    private static final Path edgePath = Paths.get("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");


    /**
     * Maximizing using screen dimensions
     */
    @Test
    public void maximize_Window_Toolkit() throws InterruptedException {
        System.out.println(HEIGHT + "----" + WIDTH);

        /*
         *
         * When defining a new browser type in Playwright, it's essential to use the BrowserType.
         * LaunchOptions interface to set configuration options for the browser instance.
         * In the code snippet new BrowserType.LaunchOptions().setHeadless(false), the BrowserType.
         * LaunchOptions is used to create a new instance of the LaunchOptions class, which sets properties for the browser launch.
         * By setting headless to false, the browser will launch in a non-headless mode, allowing you to interact with it visually.
         * This approach provides more flexibility and control when creating new browser instances in Playwright.
         */

        //defaultBrowserLaunch("https://google.co.in"); from BaseFunction class

        LaunchOptions headedBrowser = new BrowserType.LaunchOptions().setHeadless(false);

        //new Chromium browser
        Browser browser = playwright.chromium().launch(headedBrowser);

        //maximizing the browser window
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize((int) WIDTH, (int) HEIGHT));

        executePage(browserContext);
    }
    //https://peter.sh/experiments/chromium-command-line-switches/


    /**
     * Maximizing using arguments
     */
    @Test
    void window_start_Maximized() throws InterruptedException {
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

    /**
     * Set edge browser path from local
     * @throws InterruptedException
     */
    @Test
    void set_Executable_Path() throws InterruptedException {
        ArrayList<String> argument = new ArrayList<>();
        argument.add("--start-maximized");

        //Set channel and arguments
        LaunchOptions headedBrowser = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setExecutablePath(edgePath)
                .setArgs(argument);

        //new chromium browser
        Browser browser = playwright.chromium().launch(headedBrowser);

        //maximizing the browser window
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

        executePage(browserContext);
    }

    /**
     * Launch a browser in non Incognito browser mode
     */
    @Test
    public void nonIncognito_Browser() throws InterruptedException {

        BrowserContext browserContext = playwright.chromium().launchPersistentContext(Paths.get(localChromePath.toUri()), new BrowserType.LaunchPersistentContextOptions().setHeadless(false));

        //use chrome history from app local data by providing the location path, if empty means does not load local data
        //BrowserContext browserContext = playwright.chromium().launchPersistentContext(Paths.get(localChromePath.toUri()), new BrowserType.LaunchPersistentContextOptions().setHeadless(false));

        executePage(browserContext);
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

    public static void tearDown(Page page){
        //close the web page
        page.close();
        //close the playwright object
        playwright.close();
    }
}


