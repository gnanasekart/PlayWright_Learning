package Handling_User_Gestures;

import Base.BaseFunction;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class HandlingSliders extends BaseFunction {

    @Test
    public void TestSliders() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://jqueryui.com/resources/demos/slider/default.html");
        Locator slider = page.locator("//*[@id=\"slider\"]/span");
        Thread.sleep(2000);

        page.mouse().move(
                slider.boundingBox().x + slider.boundingBox().width / 2,
                slider.boundingBox().y + slider.boundingBox().height / 2
        );

        page.mouse().down();

        page.mouse().move(
                slider.boundingBox().x + 400,
                slider.boundingBox().y + slider.boundingBox().height / 2
        );

        page.mouse().up();
    }
}
