import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class postUserTest {

    @Test
    public void testCreateUser() {

        // SSL fix
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://reqres.in";

        String postRequestBody = """
                {
                  "name": "morpheus",
                  "job": "leader"
                }
                """;

        Response postResponse =
                given()
                        .contentType("application/json")
                        .body(postRequestBody)
                        .when()
                        .post("/api/users")
                        .then()
                        .extract().response();

        Assert.assertEquals(postResponse.getStatusCode(), 201);
    }
}
