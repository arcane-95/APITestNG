package org.example;

import org.example.utils.APIKey;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParamTest {

    /*
    * Define Path & Query parameters
    * https://reqres.in/api/users?page=2&id=5
    */
    @Test
    void pathAndQueryParameterTest() {

        given()
                .header("x-api-key", APIKey.getAPIKey())
                .pathParam("userPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 7)
                .when()
                .get("https://reqres.in/api/{userPath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
