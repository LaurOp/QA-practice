package Ex5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class flow13 {
    static WebDriver driver;

    @BeforeMethod
    public void driverSetup(){
        // 1
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test // ex 13
    public void verifyQuantityInCart(){
        // 2
        driver.get("http://automationexercise.com/");

        // 3
        // wait until page is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));

        // 4 let's randomize the process
        List<WebElement> viewProductButtons = driver.findElements(By.xpath("//div[@class='features_items']//a[contains(@href, 'product')]"));
        Random r = new Random();
        viewProductButtons.get(r.nextInt(0,viewProductButtons.size())).click();

        // 5
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']")));

        // 6
        driver.findElement(By.id("quantity")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, "4");

        // 7
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='btn btn-default cart']"));
        addToCartButton.click();

//        WebElement continueShopping = driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"));

//        JavascriptExecutor executor = (JavascriptExecutor)driver;   // js executor that sends the click directly to the input
//        executor.executeScript("arguments[0].click();", continueShopping);

        // 8
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']//a")));
        WebElement viewCart = driver.findElement(By.xpath("//div[@class='modal-content']//a"));
        viewCart.click();

        // 9
        try{
            WebElement justAddedQuantity = driver.findElement(By.xpath("//tbody//td//button")); // throws exception if nothing found
            if (!justAddedQuantity.getText().equals("4")){
                throw (new Exception("Bad quantity"));
            }

            // clear the shopping cart:
            WebElement deleteElement = driver.findElement(By.xpath("//tbody//td[@class='cart_delete']//a"));
            deleteElement.click();
            System.out.println("All good!");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}
