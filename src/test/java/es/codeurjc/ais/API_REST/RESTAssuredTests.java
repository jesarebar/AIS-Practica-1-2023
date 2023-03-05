package es.codeurjc.ais.API_REST;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RESTAssuredTests {
    @Test
    public void test1() {
        given().param("topic", "drama").
                when().get("http://localhost:8080/api/books/").
                then().statusCode(200).body("size()", equalTo(10));
    }
}
