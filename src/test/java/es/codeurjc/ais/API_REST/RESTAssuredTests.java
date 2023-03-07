package es.codeurjc.ais.API_REST;


import es.codeurjc.ais.review.Review;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;


public class RESTAssuredTests {

    String BASE_URI = "http://localhost:8080/api/books/";
    @Test
    public void test1() {
        given().param("topic", "drama")
                .when().get(BASE_URI)
                .then().assertThat().statusCode(200).assertThat().body("size()", equalTo(10));
    }

    @Test
    public void test2() throws JSONException {

        Response fantasyResponse = given().param("topic", "fantasy")
                .when().get(BASE_URI)
                .then().extract().response();

        JsonPath json_fantasyResponse = from(fantasyResponse.getBody().asString());

        ArrayList<String> idList = json_fantasyResponse.get("id");

        String firstBookid = idList.get(0);

        JSONObject review = new JSONObject();

        review.put("nickname", "Tom");
        review.put("content", "I liked it");
        review.put("bookId", firstBookid);

        given().request()
                .body(review.toString()).contentType(ContentType.JSON)
                .when().post(BASE_URI + firstBookid + "/review")
                .then().assertThat().statusCode(201);

        given().get(BASE_URI + firstBookid)
                .then().assertThat().body("reviews", notNullValue());

    }

    @Test
    public void test3() throws JSONException {
        String id = "OL15358691W";

        JSONObject review = new JSONObject();

        review.put("nickname", "Peter");
        review.put("content", "I didn't like it");
        review.put("bookId", id);

        Response rev = given().request().body(review.toString()).contentType(ContentType.JSON)
                .when().post(BASE_URI + id + "/review")
                .then().assertThat().statusCode(201)
                .extract().response();

        long reviewID = rev.as(Review.class).getId();
        given()
                .when()
                .delete(BASE_URI + id + "/review/" + reviewID).
                then().assertThat().statusCode(204);

    }
}
