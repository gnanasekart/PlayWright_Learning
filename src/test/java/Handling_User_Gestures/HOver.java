package Handling_User_Gestures;

import Base.BaseFunction;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class HOver extends BaseFunction {
    private static Page page;

    @Test
    public void HOver_Test() {
        page = launchBrowserWithMaximize("chrome", "https://www.way2automation.com/");

        //Hover on the element
        page.locator("//*[@id='menu-item-27580']/a/span[2]").hover();
        page.locator("//*[@id='menu-item-27580']/a").click(setClickTimeout);
    }
}
