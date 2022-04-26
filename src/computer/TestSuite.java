package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        // Click on Computer
        clickOnElement(By.xpath("//div[@class='header-menu']/ul/li[1]"));
        // Click on Desktop
        clickOnElement(By.xpath("//div[@class='page-title']/h1"));
        // Price before filter
        List<WebElement> beforeFilterPrice = driver.findElements(By.xpath("item-grid"));
        // Remove the $ sign & store in array
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement price : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(price.getText().replace("$", "")));
        }
        // Price low to high
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Price: Low to High");
        List<WebElement> afterFilterPrice = driver.findElements(By.xpath("item-grid"));
        // Remove the $ sign & store in array
        List<Double> afterFilterPriceList = new ArrayList<>();
        for (WebElement p : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        // Sorting the price list
        Collections.sort(beforeFilterPriceList);
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // Click on computer top menu
        clickOnElement(By.xpath("//div[@class='header-menu']/ul/li[1]"));
        // click on desktop side menu
        clickOnElement(By.xpath("//ul[@class='sublist']/li[1]/a"));
        //Select order by Name: a to z
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");

        Thread.sleep(2000);
        // Click on add to cart
        clickOnElement(By.xpath("//div[@class='product-item']/div[2]/div[3]/div[2]/button[1]"));
        // Verification of text
        verifyText("Build your own computer", By.xpath("//h1[contains(text(),'Build your own computer')]"), "Build your own computer");
        // Select 2.2Ghz
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"),"2.2 GHz Intel Pentium Dual-Core E2200");

        Thread.sleep(2000);
        // Select 8GB
        selectByVisibleTextFromDropDown(By.id("product_attribute-2"), "8GB {+$60.00]");

        Thread.sleep(2000);
        // Click on Hdd
        clickOnElement(By.id("product_attribute_3_7"));

        Thread.sleep(2000);
        // Click on Os
        clickOnElement(By.id("product_attribute_4_9"));

        Thread.sleep(2000);
        // Click on total commander
        clickOnElement(By.id("product_attribute_5_12"));

        Thread.sleep(2000);

        // Verify the Price
        verifyText("$1,475.00",By.id("price-value-1"),"Price validation");
        // Click on cart button
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        verifyText("The Product has been added to your shopping cart", By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"),"The Product has been added to your shopping cart");
        // Closing green bar by clicking on cross button
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));
        // Mouse hovering on shopping cart
        mouseHoverAndClick(By.xpath("//span[contains(text(),'Shopping cart')]"),By.xpath("//button[contains(text(),'Go to cart')]"));
        // Verify the message "Shopping Cart"
        verifyText("Shopping Cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping Cart");
        // Clear the text from the field
        Thread.sleep(2000);
        clearTextFromField(By.xpath("//td[@class='quantity']/input"));
        // send keys to field
        Thread.sleep(2000);
        sendTextToElement(By.xpath("//input[@id='itemquantity11234']/input"),"2");
        // click on cart update
        clickOnElement(By.xpath("//button[@id='updatecart']"));
        // Verify the value
        verifyText("2,950.00", By.xpath("//body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[3]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/span[1]/strong[1]"),"Verify price");
        // Clict the T&C box
        clickOnElement(By.id("termsofservice"));
        // click on checkout button
        clickOnElement(By.name("checkout"));
        //Verify the text
        verifyText("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"),"Welcome, Please Sign In!");
        // Click the checkout as a guest
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        // Fill all the mendatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"),"xyz");
        sendTextToElement(By.id("BillingNewAddress_LastName"),"uvw");
        sendTextToElement(By.id("BillingNewAddress_Email"),"xyz@gmail.com");
        Thread.sleep(8000);
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"),"UK");
        sendTextToElement(By.id("BillingNewAddress_City"),"London");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"Church Street");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"SW1E 8EX");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"0123456789");

        // Click on continue button
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']/button[4]"));
        Thread.sleep(8000);
        // Click on next day air ($0.00)
        clickOnElement(By.xpath("//ul[@class='method-list']/li[2]/div[1]/input"));
        // Click on continue button
        clickOnElement(By.xpath("//form[@id='co-shipping-method-form']/div[2]/button"));
        //click on credit card radio button
        clickOnElement(By.xpath("//ul[@class='method-list']/li[2]/div/div[2]/input"));
        // Click on continue button
        clickOnElement(By.xpath("//div[@id='checkout-step-payment-method']/div/button"));
        Thread.sleep(5000);
        // Select the master card
        selectByVisibleTextFromDropDown(By.id("CreditCardType"),"Master Card");
        // Fill the requested credit/debit card details
        sendTextToElement(By.id("CardholderName"),"xyz");
        sendTextToElement(By.id("CardNumber"),"1234 5678 9102 4567");
        sendTextToElement(By.id("ExpireMonth"),"03");
        sendTextToElement(By.id("ExpireYear"),"2025");
        sendTextToElement(By.id("CardCode"),"079");
        // Click on continue button
        clickOnElement(By.xpath("//div[@id='payment-info-buttons-container']/button"));
        // Verify the payment method is credit card
        verifyText("Payment Method", By.xpath("//li[@class='payment-method']/span[2]"),"Credit Card");
        // Verify the shipping method "Next Day Air"
        verifyText("Shipping method", By.xpath("//li[@class='shipping-method']/span[2]"),"Next Day Air");
        // Verify the total amount
        verifyText("$2,950.00", By.xpath("//div[@class='table-wrapper']/table[1]/tbody/tr/td[6]/span"),"Total Amount");
        // Click on confirm
        clickOnElement(By.xpath("//div[@id='confirm-order-buttons-container']/button"));

        Thread.sleep(5000);
        // Verify text "Thank You"
        verifyText("Thank You", By.xpath("//div[@class='page-title']/h1\"),\"Verify thank you text"),"Thank You");
        // Verify the message of Successfully order processed
        verifyText("Your order has been successfully processed!",By.xpath("//div[@class='section order-completed']/div[1]\"),\"Verify successfully processed text"),
                "Your order has been successfully processed!");
        // Click on continue button
        clickOnElement(By.xpath("//div[@class='section order-completed']/div[3]/button"));
        // Verify the text "Welcome to our store"
        verifyText("Welcome to our store", By.xpath("//div[@class='topic-block-title']/h2\"),\"Verify welcome to iur store"),
                "Welcome to our store");
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}















