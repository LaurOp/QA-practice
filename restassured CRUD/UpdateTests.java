import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class UpdateTests extends Base{

    @Test
    public void updateUser(@Optional("2") String id){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"name\":\"morpheus\",\"job\":\"zion resident\"}")
                        .put("/users/" + id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    @Test
    public void patchUser(@Optional("2") String id){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"name\":\"morpheus\",\"job\":\"zion resident\"}")
                        .patch("/users/" + id)
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }



}
