import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Ex1 {
    @Test

    public void getFact(){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("https://catfact.ninja/fact")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        Gson gson = new Gson();
        JsonObject data = gson.fromJson(response, JsonObject.class);
        System.out.println(data.getAsJsonObject().get("fact").getAsString());
    }
}
