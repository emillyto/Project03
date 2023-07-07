package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    // That method
    public static String generateToken() {
        String body = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        Response response = given().contentType(ContentType.JSON).body(body).
                post("https://restful-booker.herokuapp.com/auth");


        return response.jsonPath().getString("token");
    }
}
