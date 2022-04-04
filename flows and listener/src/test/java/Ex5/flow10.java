package Ex5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class flow10 {
    static WebDriver driver;

    @BeforeMethod
    public void driverSetup(){
        // 1
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test // ex 10
    public void verifySubscription() throws InterruptedException {
        // 2
        driver.get("http://automationexercise.com/");

        // 3
        // wait until page is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));

        // 4
        WebElement element = driver.findElement(By.xpath("//footer"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // 5
        WebElement subscriptionText = driver.findElement(By.xpath("//footer//div[@class='single-widget']//h2"));
        if(!subscriptionText.getText().equals("SUBSCRIPTION")){
            org.testng.Assert.fail("SUBSCRIPTION is misspelled");
        }

        // 6
        WebElement enterEmail = driver.findElement(By.id("susbscribe_email"));
        enterEmail.sendKeys("oprealaur@yahoo.com");
        WebElement submitEmail = driver.findElement(By.id("subscribe"));
        submitEmail.click();

        // 7
        WebElement succesEmail = driver.findElement(By.id("success-subscribe"));
        System.out.println("Text is visible: " + succesEmail.isDisplayed());
    }


    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}
