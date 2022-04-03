import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Random;

public class Ex4 {
    @Test
    public void scrollAddRandom() throws InterruptedException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://mihaighilenschi.wixsite.com/stestinggood");
        WebElement element = driver.findElement(By.xpath("//span[text()='Best Deals']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);     // scrolling script



        int valRandom = new Random().nextInt(20) + 2;   // random value

        var items = driver.findElements(By.xpath("//div[@id='comp-kmxemnwq']//div[contains(@class, 'slick-slide')]//a"));
        int pozRandom = new Random().nextInt(5); // pick a random item; nu imi functioneaza pentru elemente ce nu sunt in momentul acesta pe ecran

        items.get(pozRandom).click();

        driver.findElement(By.xpath("//div[@class='_2uERl']//input")).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//div[@class='_2uERl']//input")).sendKeys(""+valRandom);   // insert the random values
        driver.findElement(By.xpath("//button[@class='buttonnext1002411228__root Focusable1046997859__root Button2148838772__root StatesButton1285133605__root AddToCartButton3061789056__addToCartButton _36nLb']")).click();
        Thread.sleep(5000);

        var price = driver.findElement(By.xpath("//span[@data-hook='formatted-primary-price']")).getText().toString();       // get object price
        price = price.substring(0, price.length()-3).replace(',','.');
        var priceFloat = Float.parseFloat(price);

        //driver.findElement(By.xpath("//div[@class='bQgup']")).click();     //click on the shopping cart icon

        var subtotal = driver.findElement(By.xpath("//div[@class='cart-widget-total']")).getText().toString();
        subtotal = subtotal.substring(0, subtotal.length()-3).replace(',','.');
        var subtotalFloat = Float.parseFloat(subtotal);

        if (subtotalFloat == priceFloat * valRandom){    // check if well summed
            System.out.println("Good sum");
        }
        else{
            System.out.println("Bad sum");
        }

        Thread.sleep(5000);
        driver.close();
    }
}

