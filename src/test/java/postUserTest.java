import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class postUserTest {
    private static final String API_KEY = "reqres-free-v1";

    @Test
    public void testCreateUser() {


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
                        .when().header("x-api-key", API_KEY)
                        .post("/api/users")
                        .then()
                        .extract().response();

        Assert.assertEquals(postResponse.getStatusCode(), 201);
    }
}
