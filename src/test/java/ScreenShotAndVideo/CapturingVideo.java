package ScreenShotAndVideo;

import Base.BaseFunction;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class CapturingVideo extends BaseFunction {

    @Test
    void recordVideo() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("./src/test/resources/ScreenShot/"))
                .setRecordVideoSize(800, 600));
        Page page = browserContext.newPage();
        try {
            page.navigate("https://www.amazon.in/");
            page.locator("//button[@type=\"submit\"]").click(setClickTimeout);
            // locator("//*[@data-nav-role='signin']") resolves to 2 elements on Amazon.
            // Use .first() to target the first occurrence (or use .nth(index) for a specific one).
            page.locator("//*[@data-nav-role=\"signin\"]").first().hover(new Locator.HoverOptions().setTimeout(2000));
            page.locator("//*[@data-nav-role=\"signin\"]").first().click(setClickTimeout);
            Thread.sleep(3000);
        } finally {
            page.close();
            browserContext.close();
            playwright.close();
            clearScreenshotDirectory();
        }
    }

    @Test
    void traceViewer() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                        .setViewportSize((int) WIDTH, (int) HEIGHT)
                .setRecordVideoDir(Paths.get("./src/test/resources/ScreenShot/")));
        Tracing tracing = browserContext.tracing();
        tracing.start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(false)
        );
        Page page = browserContext.newPage();

        try {
            page.navigate("https://www.amazon.in/");
            page.locator("//button[@type=\"submit\"]").click(setClickTimeout);
            // avoid strict mode violation: use .first() when locator matches multiple elements
            page.locator("//*[@data-nav-role=\"signin\"]").first().hover(new Locator.HoverOptions().setTimeout(2000));
            page.locator("//*[@data-nav-role=\"signin\"]").first().click(setClickTimeout);
            Thread.sleep(3000);
        } finally {
            browserContext.tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("traceViewer.zip"))
            );
            page.close();
            browserContext.close();
            playwright.close();
            //clearScreenshotDirectory();
        }
    }
}
