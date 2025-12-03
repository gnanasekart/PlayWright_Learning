package Handling_User_Gestures;

import Base.BaseFunction;
//import com.microsoft.playwright.Locator.TypeOptions;
import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class HandlingKeyboardEvents extends BaseFunction {

    @Test
    public void testKeyboardEvents() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://google.com");

        page.keyboard().press("Control+Shift+I");
        Thread.sleep(3000);

        page.keyboard().press("Control+Shift+I");
        Thread.sleep(3000);

        page.keyboard().type("Selenium Playwright", new Keyboard.TypeOptions().setDelay(100));
        Thread.sleep(3000);

        page.keyboard().press("Enter");
        Thread.sleep(5000);

        //page.locator("#APjFqb").fill("Java", new Locator.FillOptions().wait(100));

        page.keyboard().press("Control+A");
        Thread.sleep(2000);

        page.keyboard().press("Control+C");
        Thread.sleep(2000);

        page.keyboard().press("Control+V");
        Thread.sleep(5000);

        for (int i = 0; i < 3; i++) {
            page.keyboard().press("ArrowLeft");
        }

    }

}
