import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.math.NumberUtils.max;

public class TestTren extends Base{

    @Test
    public void testTren() {
        By clickSee = By.xpath("//button[@id='button-available-places']");
        By text = By.xpath("//div[@id='div-step-2-available-places-result']//ul[1]//li[last()]");
        By text2 = By.xpath("//div[@id='div-step-2-available-places-result']//ul[last()]//li[last()]");
        By tren = By.xpath("//div[@id='div-step-2-available-places-result']//div//div//span");
        By tren2 = By.xpath("//div[@id='div-step-2-available-places-result']//div//div[last()]//span");
        By furnizate = By.xpath("//div[@id='div-step-2-available-places-result']//div//p");

        driver.get("https://bilete.cfrcalatori.ro/ro-RO/Buying?Step=2&RequestIdString=CfDJ8HrOmSFixfVKtOkHdavVMsgVJeq_RTCqsmBbRMpAFb-BsYvIBjnFi9Rt8-HtqXUuN7Yrw0avDWEURIJgSqDhNeMGjtORL3-kYPL9dqwQlSxbxKTCb_SAUsTNVv1r50_MzA");
        driver.findElement(clickSee).click();
        String dus = driver.findElement(text).getText();
        String trendus = driver.findElement(tren).getText();
        String dus2 = driver.findElement(text2).getText();
        String trendus2 = driver.findElement(tren2).getText();
        String oraAct = driver.findElement(furnizate).getText();

        driver.get("https://bilete.cfrcalatori.ro/ro-RO/Buying?Step=2&RequestIdString=CfDJ8HrOmSFixfVKtOkHdavVMsi1JspesuUpzAdEx-LnmNMTaclx0TgdY9H3-cMyFa9dnT0pYCNuqbTX01EXSHTaEA6EscLkLjxgGpU3vXZozGlzZGxz7b61Zfz2FS8v_5vTgQ");
        driver.findElement(clickSee).click();
        String intors = driver.findElement(text).getText();
        String trenintors = driver.findElement(tren).getText();

        int nrLinii = max((trendus+dus).length(), (trendus2+dus2).length()) + 15;

        String rez =  oraAct
                    + "\n"+ new String(new char[nrLinii]).replace("\0", "-") + "\n"
                    + trendus + "  *" + dus + "*\n"
                    + trendus2+ "  *" + dus2 + "*\n\n"
                    + trenintors + "\n*" + intors + "*\n"
                    + new String(new char[nrLinii]).replace("\0", "-");

        System.out.println(rez);

        driver.get("https://write-box.appspot.com/");
        WebElement textbox = driver.findElement(By.xpath("//*[@id='editor']"));
        textbox.sendKeys(rez);
        textbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        textbox.sendKeys(Keys.chord(Keys.CONTROL, "c"));


    }
}
