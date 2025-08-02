package org.example;

import io.restassured.http.ContentType;
import org.example.utils.APIKey;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class    HTTPRequestsTest {

    int id;

    @Test(priority = 1)
    public void getUsers() {
        given()
                .header("x-api-key", APIKey.getAPIKey())
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUser() {
        HashMap<String, String> details = new HashMap<>();
        details.put("name", "eudora");
        details.put("job", "mage");

        id = given()
                .header("x-api-key", APIKey.getAPIKey())
                .contentType(ContentType.JSON)
                .body(details)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                /*.then()
                .statusCode(201)
                .log().all();*/
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {
        HashMap<String, String> details = new HashMap<>();
        details.put("name", "alucard");
        details.put("job", "fighter");

        given()
                .header("x-api-key", APIKey.getAPIKey())
                .contentType(ContentType.JSON)
                .body(details)
                .when()
                .put("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void deleteUser() {
        given()
                .header("x-api-key", APIKey.getAPIKey())
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(204)
                .log().all();
    }
}
