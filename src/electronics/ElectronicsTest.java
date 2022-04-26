package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.Random;

public class ElectronicsTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void textVerified() throws InterruptedException {
        mouseHoverAndClick(By.xpath("//ul[@class='top-menunotmobile']/li[2]"), By.xpath("//ul[@class='top-menu notmobile']/li[2]/ul/li[2]"));
        // Verify the text
        verifyText("Electronics", By.xpath("//div[@class='center-2']/div/div[1]/h1"), "Verify cellphones text");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfully() throws InterruptedException {
        // Mouse Hover on “Electronics” Tab
        mouseHooveringToElement(By.xpath("//ul[@class='top-menu notmobile']/li[2]"));
        // Mouse Hover on “Cell phones” and click
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
        // Verify the text “Cell phones”
        verifyText("Cell phones", By.xpath("//div[@class='page category-page']/div[1]/h1"), "Verify cellphones text");
        // Click on List View Tab
        clickOnElement(By.xpath("//div[@class='product-viewmode']/a[2]"));
        Thread.sleep(2000);
        // Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//div[@class='item-grid']/div[3]/div/div[2]/h2/a"));
        // Verify the text “Nokia Lumia 1020”
        verifyText("Nokia Lumia 1020", By.xpath("//div[@class='product-name']/h1"), "Verify nokia lumia 1020 text");
        // Verify the price “$349.00”
        verifyText("$349.00", By.xpath("//div[@class='prices']/div/span"), "Price verify");
        // Change quantity to 2
        clearTextFromField(By.id("product_enteredQuantity_20"));
        Thread.sleep(2000);
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");
        Thread.sleep(2000);
        // Click on “ADD TO CART” tab
        clickOnElement(By.id("add-to-cart-button-20"));
        // Verify the Message "The product has been added to your shopping cart" on Top green Bar
        verifyText("The product has been added to your shopping cart", By.xpath("//div[@id='bar-notification']/div/p"), "Verify sgopping cart text");
        // After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//div[@id='bar-notification']/div/span"));
        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverAndClick(By.xpath("//li[@id='topcartlink']"), By.xpath("//button[contains(text(),'Go to cart')]"));
        // Verify the message "Shopping cart"
        verifyText("Shopping cart", By.xpath("//div[@class='page-title']/h1"), "Verify shopping cart text");
        // Verify the quantity is 2
        // VerifyText("2", By.xpath("//td[@class='quantity']/input"), "Verify quantity");
        // Verify the Total $698.00
        verifyText("$698.00", By.xpath("//td[@class='subtotal']/span"), "Verify price");
        // Click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));
        // Click on checkout
        clickOnElement(By.id("checkout"));
        // Verify the Text “Welcome, Please Sign In!”
        verifyText("Welcome, Please Sign In!", By.xpath("//div[@class='page-title']/h1"), "Verify welcome sign in text");
        // Click on “REGISTER” tab
        clickOnElement(By.xpath("//div[@class='buttons']/button[2]"));
        // Verify the text “Register”
        verifyText("Register", By.xpath("//div[@class='page-title']/h1"), "Register text validation");
        // Fill the mandatory fields
        sendTextToElement(By.id("FirstName"), "xyz");
        sendTextToElement(By.id("LastName"), "uvw");
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.click();
        // Random generator class
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        emailField.sendKeys("username" + randomInt + "@gmail.com");
        sendTextToElement(By.id("Password"), "123456");
        sendTextToElement(By.id("ConfirmPassword"),"123456");
        // Click on “REGISTER” Button
        clickOnElement(By.id("register-button"));
        // Verify the message “Your registration completed”
        verifyText("Your registration completed",By.xpath("//div[@class='page-body']/div[1]"),
        "Your registration completed text");
        // Click on “CONTINUE” tab
        clickOnElement(By.xpath("//div[@class='buttons']/a"));
        // Verify the text “Shopping cart”
        verifyText("Shopping cart",By.xpath("//div[@class='page-title']/h1"),
        "Verify shopping cart text");
        // click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));
        // Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));
        // Fill the Mandatory fields
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"),"Albania");
        sendTextToElement(By.id("BillingNewAddress_City"),"surat");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"999 baker road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"56235");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"9898989898");
        // Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@class='buttons']/button[4]"));
        // Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));
        // Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@id='shipping-method-buttons-container']/button"));
        // Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//div[@id='payment-method-buttons-container']/button"));
        // Select “Visa card” from dropdown
        // Fill all the requested payment details
        sendTextToElement(By.id("CardholderName"),"pin");
        sendTextToElement(By.id("CardNumber"),"1234 4567 7890 9876");
        selectByIndexFromDropDown(By.id("ExpireYear"),8);
        sendTextToElement(By.id("CardCode"),"456");
        // Click on “CONTINUE”CHECKOUT”
        clickOnElement(By.xpath("//div[@id='payment-info-buttons-container']/button"));
        // Verify “Payment Method” is “Credit Card”
        verifyText("Credit Card",By.xpath("//li[@class='payment-method']/span[2]"),"Credit card validation");
        // Verify “Shipping Method” is “2nd Day Air”
        verifyText("2nd Day Air",By.xpath("//li[@class='shipping-method']/span[2]"),"Shipping method validation");
        // Verify Total is “$698.00”
        verifyText("$698.00",By.xpath("//td[@class='subtotal']/span"),"Amount validation");
        // Click on “CONFIRM”
        clickOnElement(By.xpath("//div[@id='confirm-order-buttons-container']/button"));
        // Verify the Text “Thank You”
        verifyText("Thank you",By.xpath("//div[@class='page checkout-page order-completed-page']/div[1]/h1"),
        "Thank you text validation");
        // Verify the message “Your order has been successfully processed!”
        verifyText("Your order has been successfully processed!",
            By.xpath("//div[@class='page checkout-page order-completed-page']/div[2]/div/div[1]"),
        "yoru order has been successfully processed");
        // Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@class='section order-completed']/div[3]/button"));
        // Verify the text “Welcome to our store”
        verifyText("Welcome to our store",By.xpath("//div[@class='topic-block-title']/h2"),"Welcome to store text");
        // Click on “Logout” link
        clickOnElement(By.xpath("//div[@class='header-links']/ul/li[2]"));
        // Verify the URL is “https://demo.nopcommerce.com/”
        String actualUrl=driver.getCurrentUrl();
        String expectedUrl="https://demo.nopcommerce.com/";
        Assert.assertEquals(actualUrl,expectedUrl);

    }

    @After
    public void tearDown() {
        //closeBrowser();

    }
}