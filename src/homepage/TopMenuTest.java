package homepage;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        List<WebElement> topMenuName = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']"));
        for (WebElement name : topMenuName) {
            if (name.getText().equalsIgnoreCase(menu)) {
                name.click();
            }
        }
    }


    @Test
    public void verifyElectronics() {
        selectMenu("Electronics");
        verifyText("Electronics", By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"), "Electronics");
    }

    @Test
    public void verifyApparel() {
        selectMenu("Apparel");
        verifyText("Apparel", By.xpath("//body/div[6]/div[2]/ul[1]/li[3]/a[1]"), "Apparel");
    }
    /*@Test
    public void verifyComputersPageNavigation(){
        selectMenu("Computers");
        verifyText("Computers",By.xpath("//h1[contains(text(),'Computers')]"),"Computer Page Navigation Validation");
    }*/

    @Test
    public void verifyDigitalDownloads() {
        selectMenu("Digital downloads");
        verifyText("Digital downloads", By.xpath("//body/div[6]/div[2]/ul[1]/li[4]/a[1]"), "Digital downloads");
    }

    @Test
    public void verifyBooks() {
        selectMenu("Books");
        verifyText("Books", By.xpath("//body/div[6]/div[2]/ul[1]/li[5]/a[1]"), "Books");
    }

    @Test
    public void verifyJewelry() {
        selectMenu("Jewelry");
        verifyText("Jewelry", By.xpath("//body/div[6]/div[2]/ul[1]/li[6]/a[1]"), "Jewelry");
    }

    @Test
    public void verifyGiftCards() {
        selectMenu("Gift Cards");
        verifyText("Gift Cards", By.xpath("//body/div[6]/div[2]/ul[1]/li[7]/a[1]"), "Gift Cards");
    }
}






