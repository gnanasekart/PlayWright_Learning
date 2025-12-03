package HandlingFiles;

import Base.BaseFunction;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadFiles extends BaseFunction {

    @Test
    void downloadingFileTest() {
        Page page = launchBrowserWithMaximize("chrome", "https://www.selenium.dev/downloads");
        String jarFilePath = "./src/test/resources/jar/selenium.jar";

        Locator locator = page.locator("div.card-body > p.card-text");

        Download file = null;
        for (int i = 0; i < locator.count(); i++) {
            Locator nth = locator.nth(i);
            String text = nth.textContent().trim();
            if (text != null && text.contains("Latest stable version")) {

                file = page.waitForDownload( () -> {
                    nth.locator("a").click(setClickTimeout);
                });
            }
        }
        Assert.assertNotNull(file);
        file.saveAs(Paths.get(jarFilePath));

     //write script to identify the downloaded file size in ./src/test/resources/selenium.jar and delete the file if it presents
        Path downloadedPath = Paths.get(jarFilePath);
        try {
            if (Files.exists(downloadedPath)) {
                long fileSize = Files.size(downloadedPath);
                System.out.println("Downloaded file size: " + fileSize + " bytes");
                boolean deleted = Files.deleteIfExists(downloadedPath);
                System.out.println("Deleted file: " + deleted);
            } else {
                System.out.println("File not found: " + downloadedPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
