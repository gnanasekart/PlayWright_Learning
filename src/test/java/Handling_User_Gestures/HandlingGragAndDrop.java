package Handling_User_Gestures;

import Base.BaseFunction;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class HandlingGragAndDrop extends BaseFunction {

    @Test
    public void TestSliders() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://jqueryui.com/resources/demos/droppable/default.html");

        Locator draggable = page.locator("#draggable");
        Locator droppable = page.locator("#droppable");

        Thread.sleep(2000);

        page.mouse().move(
                draggable.boundingBox().x + draggable.boundingBox().width / 2,
                draggable.boundingBox().y + draggable.boundingBox().height / 2
        );

        page.mouse().down();

        page.mouse().move(
                droppable.boundingBox().x + droppable.boundingBox().width / 2,
                droppable.boundingBox().y + droppable.boundingBox().height / 2
        );

        page.mouse().up();
    }
}
