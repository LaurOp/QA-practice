package Tests;

import Base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class Tema1 extends Base {
    @Test

    public void addRemoveElem() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com");
        driver.findElement(new By.ByXPath("//a[text()='Add/Remove Elements']")).click();

        WebElement ptAdd = driver.findElement(By.xpath("//button"));

        for(int i = 0; i < 10; i++)
            ptAdd.click();

        List<WebElement> butoane = driver.findElements(By.xpath("//button[@class='added-manually']"));

        for (WebElement act : butoane)
            act.click();

        for(int i = 0; i < 8; i++)
            ptAdd.click();

        butoane = driver.findElements(By.xpath("//button[@class='added-manually']"));

        for(int i = 0; i < 5; i++)
            butoane.get(i).click();

        Thread.sleep(3000);
    }
}
