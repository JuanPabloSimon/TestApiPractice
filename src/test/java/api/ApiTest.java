package api;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void testGetUsers() {

        baseURI = "https://reqres.in/";
        given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void testGetUser() {
        baseURI = "https://reqres.in/";

        given()
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .log().body();
    }

    @Test
    public void testUserNotFound() {
        baseURI = "https://reqres.in/";

        given().when()
                .get("/api/user/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void testPostUser() {
        baseURI = "https://reqres.in";

        JSONObject map = new JSONObject();
        map.put("name", "JotaP");
        map.put("job", "poor");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(map.toJSONString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void testPutUser() {
        baseURI = "https://reqres.in/api";

        JSONObject map = new JSONObject();
        map.put("name", "morpheus");
        map.put("job", "DevOps");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(map.toJSONString())
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testDeletUser() {
        baseURI = "https://reqres.in";

        given().when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }
}

