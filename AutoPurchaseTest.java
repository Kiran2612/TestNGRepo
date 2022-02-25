package testNG;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class AutoPurchaseTest {

    WebDriver driver;
    @Test
    void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test(dependsOnMethods = {"openBrowser"})
    void selectPC() throws InterruptedException {

        WebElement item = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(item).perform();
        driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[1]/a")).click();

        Thread.sleep(2000);
    }
    @Test(priority = 1)
    void addToCart() {
        driver.findElement(By.id("add-to-cart-button-3")).click();
    }
    @Test(priority = 2)
    void shoppingCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"topcartlink\"]/a/span[1]")).click();
        WebElement w = driver.findElement(By.className("qty-input"));
        w.clear();
        w.sendKeys("3");
        driver.findElement(By.id("updatecart")).click();
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.className("checkout-as-guest-button")).click();
    }
    @Test(priority = 3)
    void checkoutForm() {
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Kiran");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Valand");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("kiranvaland@hotmail.com");
        driver.findElement(By.id("BillingNewAddress_Company")).sendKeys("UnifyTesting");
        WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId"));
        Select select = new Select(country);
        select.selectByVisibleText("United Kingdom");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Birstall");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("2 Lapwing");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("LE43EW");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("0771231235");
        driver.findElement(By.className("new-address-next-step-button")).click();
    }
    @Test(priority = 4)
    void shippingMethod(){
        driver.findElement(By.id("shippingoption_0")).click();
        driver.findElement(By.className("shipping-method-next-step-button")).click();
        driver.findElement(By.name("paymentmethod"));
        driver.findElement(By.className("payment-method-next-step-button")).click();
        driver.findElement(By.className("payment-info-next-step-button")).click();
        driver.findElement(By.className("confirm-order-next-step-button")).click();
    }
    @Test(priority = 5)
    void paymentMethod(){
        driver.findElement(By.id("paymentmethod_1")).click();
        driver.findElement(By.className("button-1 payment-method-next-step-button")).click();
    }
    @Test(priority = 6)
    void paymentInformation() throws InterruptedException {
        driver.findElement(By.id("CardholderName")).sendKeys("Kiran Valand");
        driver.findElement(By.id("CardNumber")).sendKeys("0011002200330044");
        driver.findElement(By.id("ExpireMonth")).findElements(By.linkText("01"));
        driver.findElement(By.id("ExpireYear")).findElements(By.linkText("2025"));
        driver.findElement(By.id("CardCode")).sendKeys("123");
        driver.findElement(By.className("button-1 payment-info-next-step-button")).click();
        Thread.sleep(2000);
    }
    @Test(priority = 7)
    void confirmOrder() throws InterruptedException {
        driver.findElement(By.className("button-1 confirm-order-next-step-button")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
