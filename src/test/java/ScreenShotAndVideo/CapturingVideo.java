package ScreenShotAndVideo;

import Base.BaseFunction;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class CapturingVideo extends BaseFunction {

    @Test
    void recordVideo() throws InterruptedException {
        try {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("./src/test/resources/ScreenShot/"))
                    .setRecordVideoSize(800, 600));
            Page page = browserContext.newPage();
            page.navigate("https://www.amazon.in/");
            page.locator("//button[@type=\"submit\"]").click(setClickTimeout);
            page.locator("//*[@data-nav-role=\"signin\"]").hover(new Locator.HoverOptions().setTimeout(2000));
            page.locator("//*[@data-nav-role=\"signin\"]").click(setClickTimeout);
            Thread.sleep(3000);
        } finally {
            clearScreenshotDirectory();
        }
    }
}
