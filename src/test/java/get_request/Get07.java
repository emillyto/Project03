package get_request;

import base_urls.PetBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get07 extends PetBaseUrl {
      /*
Given
    https://petstore.swagger.io/v2/pet/3467889
When
     User send a GET request to the URL
Then
    HTTP Status Code should be 200
And
    Response content type is "application/json"
And
    Response body should be like;
       {
        "id": 3467889,
        "category": {
            "id": 0,
            "name": "Bird"
        },
        "name": "Tweety",
        "photoUrls": [
            "string"
        ],
        "tags": [
            {
                "id": 0,
                "name": "string"
            }
        ],
        "status": "available"
       }

 */
    @Test// must be imported only by Junit other way will no work
    public void get07(){
        //Set Url
        spec.pathParams("first","pet","second",3467889);

        // Set the expected data

        // Send the request and Get response
       Response response= given(spec).get("{first}/{second}");
       response.prettyPrint();
       // Do Assertion
        response.then().statusCode(200);
                 spec.contentType(ContentType.JSON).
                 body("category.name"
                 );
         // Way 2
        JsonPath jsonPath=response.jsonPath();
        assertEquals("Bird",jsonPath.getString("category.name"));
        assertEquals("Tweety",jsonPath.getString("name"));
        assertEquals("available",jsonPath.getString("status"));












    }

}
