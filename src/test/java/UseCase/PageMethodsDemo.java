package UseCase;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.*;

public class PageMethodsDemo {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // Navigation
            page.navigate("https://example.com");
            System.out.println("Page title: " + page.title());
            System.out.println("Current URL: " + page.url());

            // Element Interaction
            page.click("text=More information"); // Click a link
            page.fill("input[name='email']", "gnana@example.com"); // Fill input
            page.type("input[name='name']", "Gnana"); // Type with delay
            page.press("input[name='name']", "Enter"); // Press Enter key

            // Locators
            Locator heading = page.locator("h1");
            System.out.println("Heading text: " + heading.textContent());

            // Waits
            page.waitForSelector("text=Thank you"); // Wait for confirmation

            // JavaScript Evaluation
            Object result = page.evaluate("() => document.title");
            System.out.println("Evaluated title: " + result);

            // Screenshots
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("page.png")));

            // Dialog Handling
            page.onDialog(dialog -> {
                System.out.println("Dialog message: " + dialog.message());
                dialog.accept(); // or dialog.dismiss();
            });
            page.evaluate("alert('Hello from Playwright!')");

            // Network Events
            page.onRequest(request -> System.out.println("Request: " + request.url()));
            page.onResponse(response -> System.out.println("Response: " + response.url()));

            // Console Logs
            page.onConsoleMessage(msg -> System.out.println("Console: " + msg.text()));

            // File Upload (if applicable)
            // page.setInputFiles("input[type='file']", Paths.get("resume.pdf"));

            // Keyboard & Mouse
            page.mouse().move(100, 200);
            page.keyboard().press("Tab");

            // Closing
            page.close();
            context.close();
            browser.close();
        }
    }
}

