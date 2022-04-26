package utilities;

import browsertesting.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;

public class Utility extends BaseTest {
    // Click on element method
    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    // Get text method
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    // Send text to element method
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    // Method to clear to text from field
    public void clearTextFromField(By by) {
        driver.findElement(by).sendKeys(Keys.CONTROL + "a");
        driver.findElement(by).sendKeys(Keys.DELETE);
    }

    //*************************************** Alert Methods ******************************************

    // Switch to alert method
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    // Accept the alert the method
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    // Dismiss Alert
    public void dismissAlert() {
        driver.switchTo().alert().accept();
    }

    // Get text from Alert
    public String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    // Send text to alert
    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    //************************************** Select Methods (From Dropdown Menu) *************************************

    // Select by Index
    public void selectByIndexFromDropDown(By by, int index) {
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    // Select by visible text
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    // Select by Value
    public void selectByValueFromDropdown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(text);
    }

    //**************************** Action Class Methods (By Dragging and Hovering Mouse) **************************

    // Drag and Drop Method
    public void dragAndDrop(By drag, By drop) {
        Actions actions = new Actions(driver);
        WebElement draggable = driver.findElement(drag);
        WebElement droppable = driver.findElement(drop);
        actions.dragAndDrop(draggable, droppable).build().perform();
    }

    // Hoovering Mouse and click the element
    public void mouseHoverAndClick(By by,By by1) throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement mouseHoover = driver.findElement(by);
        WebElement mouseHoover1=driver.findElement(by);
        Thread.sleep(3000);
       // waitUnitVisibilityOfElementLocated(by,3000);
        actions.moveToElement(mouseHoover).moveToElement(mouseHoover1).click().build().perform();
    }

    // Hoovering Mouse on element
    public void mouseHooveringToElement(By by) {
        Actions actions = new Actions(driver);
        WebElement mouseHoover = driver.findElement(by);
      //  Thread.sleep (3000);
      //  WaitUnitVisibilityOfElementLocated(by,2000);
        actions.moveToElement(mouseHoover).build().perform();
    }

    //************************************* Windows Closing Methods  ***********************************************

    // Closing all the opened windows
    public void closeAllWindows(List<String> hlist, String parentWindow) {
        for (String str : hlist) {
            if (!str.equals(parentWindow)) {
                driver.switchTo().window(str).close();
            }
        }
    }

    // Switch to parent window
    public void switchToParentWindow(String parentWindowld) {
        driver.switchTo().window(parentWindowld);
    }

    // Find switch to right window
    public boolean switchToRightWindow(String windowTitle, List<String> hlist) {
        for (String str : hlist) {
            String title = driver.switchTo().window(str).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Found the right window....");
                return true;
            }
        }
        return false;
    }

    //************************************* Others Methods  ***********************************************

    // Verify the displayed text on webpage
    public void verifyText(String expectedMessage, By by, String displayMessage) {
        String actualMessage = getTextFromElement(by);
        Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
    }

    //************************************* Waits Methods  ***********************************************

    // Wait till the visibility of element
    public WebElement waitUntilVisibilityOfElementLocated(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Fluent wait
    public WebElement waitForElementWithFluentWait(By by, int time, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }

    //**************************************** Screen Shot Methods  ***********************************************

    // Screen Shot
    public static String currentTimeStamp(){
        Date d = new Date();
        return d.toString().replace(":","_").replace(" ", "_");
    }

    //**************************************** Is Display Methods  ***********************************************

    // Verify the displayed element
    public boolean verifyThatElementIsDisplayed(By by){
        WebElement element = driver.findElement(by);
        if (element.isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    // Verify the displayed text element via getText method
    public boolean verifyThatTextIsDisplayed(By by, String text){
        WebElement element = driver.findElement(by);
        if (text.equals(element.getText())){
            return true;
        } else {
            return false;
        }
    }

    // Verify the Logo visibility
    public void verifyLogoVisible(WebElement element){
        boolean logoPresent = element.isDisplayed();
        Assert.assertTrue(logoPresent);
        if(!logoPresent){
            System.out.println("Logo not Visible");
        } else {
            System.out.println("Logo is Visible");
        }
    }

    //**************************************** Random Generator Methods  **********************************************

    // Random number generator
    public int generatorRandomNumber(){
        return (int) (Math.random() * 5000 + 1);
    }
    //Random email generator
    public String getRandomEmail(){
        String saltChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        Random random = new Random();
        while(email.length() < 10){
            int index = (int) (random.nextFloat() * saltChars.length());
            email.append(saltChars.charAt(index));
        }
        String saltStr = (email.toString() + "@gmail.com");
        return saltStr;
    }
    // Random string generator
    public static String getRandomString(int length){
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i ++){
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    //**************************************** Iframe Methods  **********************************************

    public void switchToIframe(WebElement element){
        driver.switchTo().frame(element);
    }
}
