import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateTests extends Base{

    @Test
    public void createUser() {
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                        .post("/users")
                        .then()
                        .statusCode(201)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    @Test
    public void register(){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}")
                        .post("/register")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    @Test
    public void incompleteRegister(){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"email\":\"eve.holt@reqres.in\"}")
                        .post("/register")
                        .then()
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }


    @Test
    public void login(){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                        .post("/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }

    @Test
    public void incompleteLogin(){
        String response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("{\"email\":\"eve.holt@reqres.in\"}")
                        .post("/login")
                        .then()
                        .extract()
                        .asPrettyString();

        System.out.println(response);
    }
}
