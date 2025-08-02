package org.example;

import io.restassured.http.ContentType;
import org.example.utils.POJOPostRequest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestTest {

    /*
    * Start resources/postTest.json in localhost before executing below tests.
    * CMD : json-server postTest.json --port 3000
    */

    //Send a POST request using Hashmap
    @Test
    public void testPostUsingHashmap() {
        HashMap<String, Object> details = new HashMap<>();
        details.put("id", "4");
        details.put("name", "Eudora");
        details.put("job", "mage");

        String[] locationArr = {"Spain", "Italy"};
        details.put("location", locationArr);

        given()
                .contentType(ContentType.JSON)
                .body(details)
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("id", equalTo("4"))
                .body("name", equalTo("Eudora"))
                .body("job", equalTo("mage"))
                .body("location[0]", equalTo("Spain"))
                .body("location[1]", equalTo("Italy"))
                .header("Content-Type", "application/json")
                .log().all();

        deleteUser(4);
    }

    //Send a POST request using org.json library
    @Test
    public void testPostUsingJsonLibrary() {
        JSONObject details = new JSONObject();
        details.put("id", "4");
        details.put("name", "Alucard");
        details.put("job", "fighter");

        String[] locationArr = {"India", "Australia"};
        details.put("location", locationArr);

        given()
                .contentType(ContentType.JSON)
                .body(details.toString())
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("id", equalTo("4"))
                .body("name", equalTo("Alucard"))
                .body("job", equalTo("fighter"))
                .body("location[0]", equalTo("India"))
                .body("location[1]", equalTo("Australia"))
                .header("Content-Type", "application/json")
                .log().all();

        deleteUser(4);
    }

    //Send a POST request using POJO - plain old java object
    @Test
    public void testPostUsingPOJO() {
        POJOPostRequest details = new POJOPostRequest();
        details.setId("4");
        details.setName("Lancelot");
        details.setJob("assassin");

        String[] locationArr = {"Brazil", "China"};
        details.setLocation(locationArr);

        given()
                .contentType(ContentType.JSON)
                .body(details)
                .when()
                .post("http://localhost:3000/users")
                .then()
                .statusCode(201)
                .body("id", equalTo("4"))
                .body("name", equalTo("Lancelot"))
                .body("job", equalTo("assassin"))
                .body("location[0]", equalTo("Brazil"))
                .body("location[1]", equalTo("China"))
                .header("Content-Type", "application/json")
                .log().all();

        deleteUser(4);
    }

    //Send a POST request using external JSON file
    @Test
    public void testPostUsingJSON() throws Exception {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("postBody.json")) {
            if (inputStream == null) {
                throw new FileNotFoundException("postBody.json not found in resources");
            }
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            JSONObject details = new JSONObject(jsonTokener);

            given()
                    .contentType(ContentType.JSON)
                    .body(details.toString())
                    .when()
                    .post("http://localhost:3000/users")
                    .then()
                    .statusCode(201)
                    .body("id", equalTo("4"))
                    .body("name", equalTo("Franco"))
                    .body("job", equalTo("tank"))
                    .body("location[0]", equalTo("Sweden"))
                    .body("location[1]", equalTo("Russia"))
                    .header("Content-Type", "application/json")
                    .log().all();

            deleteUser(4);
        }
    }

    //Delete user added by POST Request.
    public void deleteUser(int id) {
        given()
                .when()
                .delete("http://localhost:3000/users/" + id)
                .then()
                .log().all();
    }
}
