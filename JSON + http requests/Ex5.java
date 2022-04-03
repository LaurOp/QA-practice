import com.google.gson.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

public class Ex5 {
    @Test
    public void listItems(){
        WebDriver driver;   // setup pentru driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://mihaighilenschi.wixsite.com/stestinggood/start-your-cart");

        var items = driver.findElements(By.xpath("//div[@class='_3DNsL _1SZih']"));

        JsonObject products = new JsonObject(); // final JSON
        products.addProperty("numberOfProducts", items.size());  // list size

        JsonArray listOfProds = new JsonArray();

        for (int i = 0; i < items.size(); i++){
            JsonObject temp = new JsonObject();
                                                                                //we're using the './/' xpath to find results only in item[i]'s children
            temp.addProperty("title", items.get(i).findElement(By.xpath(".//h3")).getText());   // adding titles

            if(items.get(i).findElements(By.xpath(".//span[text()='Best Deal']")).size() > 0){   // if we see the 'Best deal' tag, set the flag as being true
                temp.addProperty("bestDeal", true);
            }
            else {
                temp.addProperty("bestDeal", false);
            }

            var price = items.get(i).findElement(By.xpath(".//span[@class='_2-l9W']")).getText();   //getting the item's price
            price = price.substring(0, price.length()-3).replace(',','.');   // delete the letters so we can parse to float
            var priceFloat = Float.parseFloat(price);

            temp.addProperty("price", priceFloat);


            listOfProds.add(temp);     // add object to jsonArray

        }

        products.add("products", listOfProds);  // add JsonArray to JsonElement(JsonObject in this case)

        Gson gson = new GsonBuilder().setPrettyPrinting().create();     // for pretty printing
        System.out.println(gson.toJson(products));

        driver.close();
    }
}
