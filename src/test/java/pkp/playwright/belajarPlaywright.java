package pkp.playwright;

import com.microsoft.playwright.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

//import io.qameta.allure.junit5.AllureJunit5;
//import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;
import java.nio.file.Paths;

//@ExtendWith(AllureJunit5.class)
public class belajarPlaywright {
    @Test
    @DisplayName("Verify Url in Playwright Java")
    public void verifyUrl(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
                page.navigate("http://www.programsbuzz.com/user/login");
                page.locator("#edit-name").type("Naruto");
                page.locator("#edit-pass").type("Uzumaki");
                String currentUrl = page.url();
                String expectedUrl = "https://www.programsbuzzz.com/user/login";
                if (currentUrl.equals(expectedUrl)){
                    System.out.println("URL is correct " + currentUrl);
                } else {
                    System.out.println("URL is incorrect. Expected: " + expectedUrl + " , but got: "+ currentUrl);
                }
//                System.out.println(currentUrl);
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Verify Placeholder Text in Playwright Java")
    public void verifyPlaceholder(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.programsbuzz.com/user/login");
        Locator searchBar = page.locator("#edit-keys");
        String placeText = searchBar.getAttribute("placeholder");

        if (placeText.contains("Enter the terms you wish to search for")){
            System.out.println("PASS");
        } else {
            System.out.println("FAIL! no such texts");
        }
    }

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
//            menentukan lokasi file yang ingin di upload
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
//        menentukan lokasi file untuk upload
        fileChooser.setFiles(Paths.get("/users/pkpho/Downloads/Aziz.jpeg"));
        page.click("input:has-text(\"upload\")");
        page.waitForLoadState();
        System.out.println(page.locator("#uploaded-files").textContent());
        page.close();
        browser.close();
        playwright.close();
    }
}
