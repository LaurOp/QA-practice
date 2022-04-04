package Ex5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

public class flow8 {
    static WebDriver driver;

    @BeforeMethod
    public void driverSetup(){
        // 1
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



    @Test   // ex 8
    public void verifyAllProds(){
        // 2
        driver.get("http://automationexercise.com/");

        // 3
        // wait until page is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));

        // 4
        WebElement productsButton = driver.findElement(By.xpath("//a[@href='/products']"));
        productsButton.click();

        // 5
        String pageTitle = driver.getTitle();

        if(!pageTitle.contains("All Products")){
            org.testng.Assert.fail("Bad redirect. Not on all products page");
        }

        // 6    wait until the list is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='features_items']")));

        // 7
        WebElement viewFirstProd = driver.findElement(By.xpath("//a[@href='/product_details/1']"));
        viewFirstProd.click();

        // 8
        if(!driver.getCurrentUrl().equals("https://automationexercise.com/product_details/1")){
            org.testng.Assert.fail("Bad redirect. Not on all first product's page");
        }

        // 9
        System.out.println("Name is displayed: " + driver.findElement(By.xpath("//div[@class='product-information']//h2")).isDisplayed());
        System.out.println("Category is displayed: " + driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Category')]")).isDisplayed());
        System.out.println("Price is displayed: " + driver.findElement(By.xpath("//div[@class='product-information']//span//span")).isDisplayed());
        System.out.println("Availability is displayed: " + driver.findElement(By.xpath("//div[@class='product-information']//p//b[contains(text(),'Availability')]")).isDisplayed());
        System.out.println("Condition is displayed: " + driver.findElement(By.xpath("//div[@class='product-information']//p//b[contains(text(),'Condition')]")).isDisplayed());
        System.out.println("Brand is displayed: " + driver.findElement(By.xpath("//div[@class='product-information']//p//b[contains(text(),'Brand')]")).isDisplayed());


    }


    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}
