import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Ex4 {
    @Test
    public void postRequest(){
        // 1
        JsonObject object = new JsonObject();
        object.addProperty("userName","theTeam");
        object.addProperty("password","MyPassword1!");

        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(object.toString())        // passing the json object as body
                        .post("https://demoqa.com/Account/v1/Login")    // request url
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        // parse the response to Gson format
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(response, JsonObject.class);
        System.out.println(response);
        // 2
        System.out.println("\nIs active? : " + data.getAsJsonObject().get("isActive"));

    }
}
