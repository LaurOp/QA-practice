import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DeleteTests extends Base{

    @Test
    public void deleteUser(@Optional("2") String id){

        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .delete("/users/" + id)
                        .then()
                        .statusCode(204)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    
}
