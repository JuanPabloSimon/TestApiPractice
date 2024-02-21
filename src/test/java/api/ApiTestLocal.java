package api;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestLocal {

    @Test
    public void testGetUsers() {
        baseURI = "http://localhost:3000";

        when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testGetUserAgainsSchema() {
        baseURI = "http://localhost:3000";

        when()
                .get("/users")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json"))
                .log().all();

    }

    @Test
    public void testGetUser() {
        baseURI = "http://localhost:3000";

        when()
                .get("users/1")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testPostUser() {
        baseURI = "http://localhost:3000";

        JSONObject body = new JSONObject();
        body.put("firstName", "Steve");
        body.put("lastName", "Jobs");
        body.put("subjectId", "1");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void testPutUser() {
        baseURI = "http://localhost:3000";

        JSONObject body = new JSONObject();
        body.put("firstName", "Steve");
        body.put("lastName", "Carell");
        body.put("subjectId", "2");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).
                body(body.toJSONString())
                .when()
                .put("/users/37b2")
                .then()
                .statusCode(200)
                .log().all();

    }


    @Test
    public void testPatchUser() {
        baseURI = "http://localhost:3000";

        JSONObject body = new JSONObject();
        body.put("firstName", "Stephen");
        body.put("lastName", "King");
        body.put("subjectId", "1");


        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body.toJSONString())
                .when()
                .patch("/users/37b2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testDeleteUser() {
        baseURI = "http://localhost:3000";

        when()
                .delete("/users/37b2")
                .then()
                .statusCode(200)
                .log().all();
    }
}
