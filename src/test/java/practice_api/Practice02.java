package practice_api;

import base_urls.Practice_url;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;

public class Practice02 extends Practice_url {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
    @Test
    public void get02(){
        spec.pathParams("first","users","second",23);
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(404).statusLine("HTTP/1.1 404 Not Found").
                header("Server","cloudflare").body("",anEmptyMap());// there is no data returned in
        // the response that's why we use anEmptyMap() to check if is empty or not

    }
}
