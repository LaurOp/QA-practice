import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Ex3 {
    @Test
    public void getRequest(){
        // GET request
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("https://api.coindesk.com/v1/bpi/currentprice.json")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();
        // Json to Gson
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(response, JsonObject.class);
        // 2
        System.out.println("chartname: " + data.getAsJsonObject().get("chartName"));
        System.out.println("GBP/rate: " + data.getAsJsonObject().get("bpi").getAsJsonObject().get("GBP").getAsJsonObject().get("rate"));
        System.out.println("EUR/rate_float: " + data.getAsJsonObject().get("bpi").getAsJsonObject().get("EUR").getAsJsonObject().get("rate_float"));



    }
}
