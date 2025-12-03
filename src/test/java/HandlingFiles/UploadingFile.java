package HandlingFiles;

import Base.BaseFunction;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UploadingFile extends BaseFunction {

    @Test
    public void uploadFileTest() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://www.way2automation.com/way2auto_jquery/registration.php#load_box");

        /*
        Directly clicking the upload button
        page.locator("#register_form > fieldset:nth-child(9) > input[type=\"file\"]");
         */

        //Iterating through all fieldsets to find the specific label - Upload label

        Locator fieldSets = page.locator("#register_form > fieldset");
        for (int i = 0; i < fieldSets.count(); i++) {
            Locator nth = fieldSets.nth(i);
            //textContents will get single string from the first label
            List<String> label = nth.locator("label").allTextContents();
            for (String labTxt: label) {
                if("Your Profile Picture".equals(labTxt.trim())) {
                    nth.locator("input[type=\"file\"]")
                            //.click(setClickTimeout) --> this will just open the popup window to select the file

                            .setInputFiles(Paths.get("./src/test/resources/img1.jpg"));
                }
            }
        }
        Thread.sleep(3000);
    }

    @Test
    void uploadMultipleFilesTest() throws InterruptedException {
        Page page = launchBrowserWithMaximize("chrome", "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_fileupload_multiple");

        page.frameLocator("#iframeResult").locator("input[type=\"file\"]")
                .setInputFiles(new Path[] {
                        Paths.get("./src/test/resources/img1.jpg"),
                        Paths.get("./src/test/resources/img2.jpeg")
                });
        page.frameLocator("#iframeResult").locator("//input[@type=\"submit\"]").click(setClickTimeout);
        String txt = page
                .frameLocator("#iframeResult")
                .locator("body.w3-container > div[class=\"w3-container w3-large w3-border\"]")
                .textContent();
        for (String s : txt.split("&")) {
            System.out.println("Uploaded file names: " + s.split("=")[1].trim());
        }

        //System.out.println("Uploaded file names: " + txt);
    }
}
