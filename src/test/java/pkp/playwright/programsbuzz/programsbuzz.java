package pkp.playwright.programsbuzz;

import com.microsoft.playwright.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class programsbuzz {
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
        Locator searchBar = page.locator("#edit-keys--2");
        String placeText = searchBar.getAttribute("keys");

        if (placeText != null && placeText.contains("Enter the terms you wish to search for.")) {
            System.out.println("PASS");
        } else if (placeText == null || placeText.isEmpty()) {
            System.out.println("FAIL! no such texts");
        } else {
            System.out.println("FAIL! Unexpected content");
        }
    }

    @Test
    @DisplayName("Assert Attribute Value in Playwright Java")
    public void assertAttributeValue(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://autopract.com/selenium/dropdown1/");
        Locator locator = page.locator("select.custom-select option >> nth=-2");
        String attributeV = locator.getAttribute("value");

        if (attributeV.equals("item3")){
            System.out.println("Attribute value is correct!");
        } else {
            System.out.println("Attribute value is incorrect.");
        }
    }
}
