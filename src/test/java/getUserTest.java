import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getUserTest {

    private static final String API_KEY = "reqres-free-v1";

    @Test
    public void testGetUsersWithDelay() {


        RestAssured.baseURI = "https://reqres.in";
        RestAssured.useRelaxedHTTPSValidation();

        Response getResponse =
                given()
                        .header("x-api-key", API_KEY)
                        .queryParam("delay", 3)
                        .when()
                        .get("/api/users")
                        .then()
                        .extract().response();

        //  Assert status code
        Assert.assertEquals(getResponse.getStatusCode(), 200);

        //  Assertions on data[0]
        Assert.assertNotNull(getResponse.jsonPath().get("data[0].id"));
        Assert.assertNotNull(getResponse.jsonPath().get("data[0].email"));
        Assert.assertNotNull(getResponse.jsonPath().get("data[0].first_name"));
        Assert.assertNotNull(getResponse.jsonPath().get("data[0].avatar"));
    }
}
