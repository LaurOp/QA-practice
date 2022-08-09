import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ReadTests extends Base {


    @Test
    public void getAllUsers() {
            String response =
                    RestAssured.given()
                            .contentType(ContentType.JSON)
                            .get("/users")
                            .then()
                            .statusCode(200)
                            .extract()
                            .asPrettyString();

            System.out.println(response);
    }


    @Test
    public void getSpecificUser(@Optional("2") String id){

        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("/users/" + id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    @Test
    public void getSpecificUserFail(@Optional("23") String id){

        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("/users/" + id)
                        .then()
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }


    @Test (timeOut = 10000)
    public void delayedGet(){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("/users?delay=3")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    @Test
    public void getAndPrintToFile() throws FileNotFoundException {
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

        PrintWriter w = new PrintWriter("out.json");        // create .json output file
        w.write(gson.toJson(jsonObject));

        w.close();
    }

}
