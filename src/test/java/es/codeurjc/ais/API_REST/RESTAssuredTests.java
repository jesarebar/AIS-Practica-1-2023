package es.codeurjc.ais.API_REST;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;

public class RESTAssuredTests {
    @Test
    public void test1() {
        given().param("topic", "drama").
                when().get("http://localhost:8080/api/books/").
                then().statusCode(200).body("size()", equalTo(10));
    }

    @Test
    public void test2() throws JSONException {

        Response fantasyResponse = given().param("topic", "fantasy")
                .when().get("http://localhost:8080/api/books/").then().extract().response();

        JsonPath json_fantasyResponse = from(fantasyResponse.getBody().asString());

        ArrayList<String> idList = json_fantasyResponse.get("id");

        String firstBookid = idList.get(0);

        JSONObject review = new JSONObject();

        review.put("id", "1");
        review.put("nickname", "Tom");
        review.put("content", "I liked it");
        review.put("bookId", firstBookid);

        given().request().body(review.toString()).contentType(ContentType.JSON).
                when().post("http://localhost:8080/api/books/"+firstBookid+"/review").
                then().assertThat().statusCode(201);

        given().get("http://localhost:8080/api/books/"+firstBookid).
                then().assertThat().body("reviews", notNullValue());

    }
}
