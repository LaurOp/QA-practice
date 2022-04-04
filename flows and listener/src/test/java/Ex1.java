import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.text.html.CSS.getAttribute;

public class Ex1 {
    static WebDriver driver;

    @BeforeMethod
    public void driverSetup(){
        // 1
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void flow() throws InterruptedException, IOException {
        // 2
        driver.get("https://www.emag.ro/");

        WebElement searchbar = driver.findElement(By.id("searchboxTrigger"));
        String textSearchbar = searchbar.getAttribute("placeholder");

        // 3
        if (!textSearchbar.equals("Ai libertatea să alegi ce vrei"))    // testam daca placeholderul este cel cautat
            org.testng.Assert.fail("Bad placeholder text");     // Daca nu, fail-uim testul

        // 4
        searchbar.sendKeys("TV");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'searchbox-submit-button')]"));
        searchButton.click();

        // 5
        // The span containing the number of tvs
        WebElement spanNrOfTVs = driver.findElement(By.xpath("//span[@class='title-phrasing title-phrasing-sm']"));
        String textTVs = spanNrOfTVs.getText();
        // split dupa un regex; obtinem primul cuv din prop (ignora caracterele spatiu)
        textTVs = textTVs.split("[ ]+")[0];
        // parse textului la Int
        System.out.println(Integer.parseInt(textTVs));

        // 6+7
         List<WebElement> pricesFirstPage = driver.findElements(By.xpath("//div[@class='clearfix pad-btm-md']//p[contains(@class,'product-new-price')]"));
         int lessthen1500 = 0;
         double smallestPrice = Double.POSITIVE_INFINITY;   // pentru a-l compara cu celelalte preturi
         int minimumPriceElementPosition = -1;
         for(int i = 0; i < pricesFirstPage.size(); i++){
             // stergem caracterele . si "
             String price = pricesFirstPage.get(i).getText().replaceAll("[\"\\.]", "").split("[, ]+")[0];
             int intPrice = Integer.parseInt(price);

             if (intPrice < 1500)
                 lessthen1500++;

             if(intPrice < smallestPrice){
                 minimumPriceElementPosition = i;
                 smallestPrice = intPrice;
             }

         }
        // output 6
         if (smallestPrice == Double.POSITIVE_INFINITY){
             System.out.println("No TVs on the first page");
         }
        System.out.println(lessthen1500 + " Tvs under 1500");

         // output 7
         List<WebElement> titlesFirstPage = driver.findElements(By.xpath("//a[@class='card-v2-title semibold mrg-btm-xxs js-product-url']"));
         WebElement titleOfMinimumElement = titlesFirstPage.get(minimumPriceElementPosition);
         System.out.println("Minimum price TV: " + titleOfMinimumElement.getText() + " costs " + smallestPrice);

         // 8
         List<WebElement> geniusBadges = driver.findElements(By.xpath("//div[@class='card-v2-badge badge-genius']"));
        System.out.println(geniusBadges.size() + " elements with a genius badge");

        // 9
        WebElement sortByButton = driver.findElement(By.xpath("//button[@class='btn btn-sm btn-alt sort-control-btn gtm_ejaugtrtnc']"));
        sortByButton.click();
        WebElement sortByReviewsButton = driver.findElement(By.xpath("//a[text()='Nr. review-uri']"));
        sortByReviewsButton.click();
        driver.get("https://www.emag.ro/search/TV/sort-reviewsdesc");


        // 10
        // The list is still ordered by number of reviews
        JsonArray tvsOnFirstPage = new JsonArray();


        // Refresh the lists of prices and titles since the elements on the page chganged
        List<WebElement> pricesFirstPage2 = driver.findElements(By.xpath("//div[@class='clearfix pad-btm-md']//p[contains(@class,'product-new-price')]"));
        List<WebElement> titlesFirstPage2 = driver.findElements(By.xpath("//a[@class='card-v2-title semibold mrg-btm-xxs js-product-url']"));

        if(pricesFirstPage2.size() != titlesFirstPage2.size()){
            org.testng.Assert.fail("Bad input data; Software bug");
        }

        for(int i = 0; i < pricesFirstPage2.size(); i++){
            JsonObject toAddToJSONArray = new JsonObject();

            toAddToJSONArray.addProperty("price", pricesFirstPage2.get(i).getText());
            toAddToJSONArray.addProperty("title", titlesFirstPage2.get(i).getText());

            tvsOnFirstPage.add(toAddToJSONArray);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();     //for pretty printing
        //System.out.println(gson.toJson(tvsOnFirstPage));

        PrintWriter w = new PrintWriter("TVs.json");        // create .json output file
        w.write(gson.toJson(tvsOnFirstPage));

        w.close();
        w.flush();


        // 11
        System.out.println("\n\n" + "List of TVs under 1200 lei:\n");
        JsonArray TVs = null;
        // read from json file
        try (Reader reader = new InputStreamReader(new FileInputStream("TVs.json"), "UTF-8")) {
            TVs = (JsonArray) JsonParser.parseReader( reader );
        } catch (Exception e) {
            System.out.println(e);
        }

        assert TVs != null;
        for(JsonElement TV : TVs){
            var price = String.valueOf(TV.getAsJsonObject().get("price"));

            price = price.replaceAll("[\"\\.]","").split(",")[0];

            // try catch that checks the price. Exception is thrown when the text is in a different format, such as
            // 'de la 2599' , and in that case we process the data differently
            try{
                if(Integer.parseInt(price) < 1200){
                    System.out.println(String.valueOf(TV.getAsJsonObject().get("title")));
                }
            }
            catch (Exception e){
                var aux = price.split("[ ,]");
                int priceInt = -1;
                // Another approach would be using Pattern and .match, but it didn't work well for me
                for(var aux2 : aux){
                    try {
                        priceInt = Integer.parseInt(aux2);
                        break;
                    }
                    catch (Exception ignored){

                    }
                }

                if(priceInt == -1){
                    org.testng.Assert.fail("Bad input. Software error");
                }
                if(priceInt < 1200){
                    System.out.println(String.valueOf(TV.getAsJsonObject().get("title")));
                }
            }


        }


    }

    @AfterMethod
    public void closeWindow(){
        driver.close();
    }
}
