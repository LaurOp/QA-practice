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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class flow19 {
    static WebDriver driver;

    @BeforeMethod
    public void driverSetup(){
        // 1
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test // ex 19
    public void brandsMenu() throws Exception {
        // 2
        driver.get("http://automationexercise.com/");

        // 3
        driver.findElement(By.xpath("//a[@href='/products']")).click();

        // 4
        WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brands_products']")));

        // 5 I'll randomize the brand picked
        Random r = new Random();
        List<WebElement> brands = driver.findElements(By.xpath("//div[@class='brands_products']//ul//li//a"));
        List<String> brandNames = new ArrayList<>();
        // list of brand names, to check for link redirection
        for (WebElement elem : brands){
            String brandname = elem.getText();
            brandNames.add(brandname);
        }

        int firstPositionPicked = r.nextInt(0,brands.size());
        brands.get(firstPositionPicked).click();
        String firstBrand = brandNames.get(firstPositionPicked);

        // 6
        firstBrand = firstBrand.split("[ \n&]")[1];
        String cap = firstBrand.substring(0, 1).toUpperCase() + firstBrand.substring(1).toLowerCase();
        // check if the actual link is the correct one (belongs to the brand we clicked on)
        if(!driver.getCurrentUrl().contains(cap)){
            System.out.println(cap);
            throw new Exception("Failed to redirect");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='features_items']")));

        // 7
        int secondPositionPicked = firstPositionPicked;
        while(secondPositionPicked == firstPositionPicked){
            secondPositionPicked = r.nextInt(0, brands.size());
        }
        brands = driver.findElements(By.xpath("//div[@class='brands_products']//ul//li//a"));

        brands.get(secondPositionPicked).click();
        String secondBrand = brandNames.get(secondPositionPicked);


        // 8
        secondBrand = secondBrand.split("[ \n&]")[1];
        cap = secondBrand.substring(0, 1).toUpperCase() + secondBrand.substring(1).toLowerCase();
        // check if the actual link is the correct one (belongs to the brand we clicked on)
        if(!driver.getCurrentUrl().contains(cap)){
            System.out.println(cap);
            throw new Exception("Failed to redirect");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='features_items']")));


        System.out.println("All good!");
    }


    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}
