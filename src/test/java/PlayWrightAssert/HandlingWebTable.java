package PlayWrightAssert;

import Base.BaseFunction;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class HandlingWebTable extends BaseFunction {

    @Test
    void webTableHandling() {
        // Implementation for handling web tables will go here
        Page page = launchBrowserWithMaximize("chrome", "https://money.rediff.com/indices/nse/NIFTY-50?src=moneyhome_nseIndices");
        Locator rowLocator = page.locator(".dataTable > tbody > tr");
        System.out.println(STR."Total number of rows in the table: \{rowLocator.count()}");

        Locator columnLocator = page.locator(".dataTable > tbody")
                .locator("tr:first-child")
                .locator("td");
        System.out.println("Total number of columns in the table: " + columnLocator.count());

        assertThat(columnLocator.nth(1)).hasText("Nifty");

        page.locator(".dataTable > tbody").allInnerTexts().forEach(System.out::println);

    }
}
