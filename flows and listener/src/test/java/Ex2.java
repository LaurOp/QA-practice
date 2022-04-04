import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Ex2 {

    static WebDriver driver;

    @BeforeMethod
    public void driverSetup(){
        // 1
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginFlow() throws InterruptedException {
        // 2
        driver.get("https://demoqa.com/automation-practice-form");

        // 3
        WebElement birthday = driver.findElement(By.id("dateOfBirthInput"));
        birthday.sendKeys(Keys.chord(Keys.CONTROL, "a"), "22 Oct 1995", Keys.ENTER);    // replace the text

        // 4
        List<WebElement> hobbies = driver.findElements(By.xpath("//div[@id='hobbiesWrapper']//input[@class='custom-control-input']"));
        Random r = new Random();

        JavascriptExecutor executor = (JavascriptExecutor)driver;   // js executor that sends the click directly to the input, so it has no overlay interruptions
        executor.executeScript("arguments[0].click();", hobbies.get(r.nextInt(0,hobbies.size())));

        // 5
        // By browse button you mean the 'Choose file' one?
        WebElement chooseFileButton = driver.findElement(By.id("uploadPicture"));
        System.out.println("Is the browse button displayed? : " + chooseFileButton.isDisplayed());
    }

    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}
