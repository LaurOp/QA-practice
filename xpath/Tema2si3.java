package Tests;

import Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Tema2si3 extends Base {
    String usrnm;
    String passw;

    @Test
    public void register() throws InterruptedException {
        driver.get("https://demoqa.com/register");
        driver.findElement(By.id("firstname")).sendKeys("Laur");
        driver.findElement(By.id("lastname")).sendKeys("Oprea");
        driver.findElement(By.id("password")).sendKeys("parola123");
        this.passw = "parola123";

        ((JavascriptExecutor) driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.get("https://www.unixtimestamp.com/");

        String cod = driver.findElement(By.xpath("//div[@class='value epoch']")).getText();
        driver.close();
        this.usrnm = cod;
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("userName")).sendKeys(cod);

        //driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-checkmark']")).click();
        //nu pot pacali un captcha

        driver.findElement(By.id("register")).click();

        Thread.sleep(3000);
    }


    @Test
    public void login() throws InterruptedException {
        driver.get("https://demoqa.com/login");
        driver.findElement(By.id("userName")).sendKeys(usrnm);
        driver.findElement(By.id("password")).sendKeys(passw);
        driver.findElement(By.id("login")).click();

        Thread.sleep(3000);
    }
}
