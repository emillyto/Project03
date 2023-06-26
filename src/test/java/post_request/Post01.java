package post_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01 extends JsonBaseHolderUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
         }
   When
       I send POST Request to the Url

   Then
       Status code is 201
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/
    @Test
    public void post01(){
        spec.pathParam("first","todos");

        // Set the expected data -->Payload -->the data to transfer (body)
        // Way 1 -
         String expectedData="{\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false }";

         // Send the request and get response
        // To send PostRequest content typy must be  declaread

       Response response= given(spec).contentType(ContentType.JSON) .body(expectedData).post("{first}");
       response.prettyPrint();

       // Do Assertion
        JsonPath jsonPath=response.jsonPath();
        assertEquals(201,response.statusCode());
        assertEquals(55,jsonPath.getInt("userId"));
        assertEquals("Tidy your room",jsonPath.getString("title"));
        assertFalse(jsonPath.getBoolean("completed"));

        // Or
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo(55),
                        "title",equalTo("Tidy your room"),
                        "completed",equalTo(false));


    }


}
