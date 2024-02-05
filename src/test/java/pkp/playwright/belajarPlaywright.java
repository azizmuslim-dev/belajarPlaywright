package pkp.playwright;

import com.microsoft.playwright.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.nio.file.Path;
import java.nio.file.Paths;

public class belajarPlaywright {
    @Test
    @DisplayName("Download File in Playwright Java")
    public void downloadFileTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://demo.automationtesting.in/FileDownload.html");
        Download waitForDownload = page.waitForDownload(page.locator("a.btn.btn-primary")::click);
//        menentukan lokasi penyimpanan hasil download
        waitForDownload.saveAs(Paths.get("Downloads/", waitForDownload.suggestedFilename()));

        System.out.println(waitForDownload.url());
        System.out.println(waitForDownload.page().title());
        System.out.println(waitForDownload.path().toString());
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Upload File in Playwright Java")
    public  void uploadFileTest(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/upload1/");
        page.setInputFiles("//input[@type='file']",
            Paths.get("/users/pkpho/Downloads/Aziz.jpeg"));
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Upload File in Playwright Java 2")
    public  void uploadFileTest2(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/upload");
        FileChooser fileChooser = page.waitForFileChooser(() -> page.click("#file-upload"));
        fileChooser.setFiles(Paths.get("/users/pkpho/Downloads/Aziz.jpeg"));
        page.click("input:has-text(\"upload\")");
        page.waitForLoadState();
        System.out.println(page.locator("#uploaded-files").textContent());
        page.close();
        browser.close();
        playwright.close();
    }
}
