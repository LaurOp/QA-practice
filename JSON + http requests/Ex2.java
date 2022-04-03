import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Ex2 {
    @Test

    public void getFact() {
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("https://catfact.ninja/facts")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        //System.out.println(response);
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(response, JsonObject.class);
        var linkuri = data.getAsJsonObject().get("links").getAsJsonArray();

        for (int i=0; i < linkuri.size(); i++){ // loop through the JsonArray
            System.out.println(linkuri.get(i).getAsJsonObject().get("url"));
        }
    }
}

