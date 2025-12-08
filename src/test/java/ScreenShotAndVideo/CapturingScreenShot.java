package ScreenShotAndVideo;

import Base.BaseFunction;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class CapturingScreenShot extends BaseFunction {

    @Test
    void capturePageScreenShot() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://www.amazon.in/");
        page.screenshot(getByPageScreenShotOptions());
        clearScreenshotDirectory();
    }

    @Test
    void captureLocatorScreenShot() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://www.amazon.in/");
        page.locator("#nav-logo-sprites").screenshot(getByLocatorScreenShotOptions());
        clearScreenshotDirectory();
    }


}
