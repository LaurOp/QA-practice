import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Tema {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.emag.ro/");

        WebElement searchBar = driver.findElement(By.id("searchboxTrigger"));
        System.out.println(searchBar.getAttribute("placeholder"));
        searchBar.sendKeys("televizor");

        WebElement searchButton = driver.findElement(new By.ByXPath("//button[contains(@class, 'searchbox-submit-button')]"));
        searchButton.click();
    }

}
