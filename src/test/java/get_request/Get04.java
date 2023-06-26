package get_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonBaseHolderUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
   I send a GET request to the Url
 And
     Accept type is "application/json"
 Then
     HTTP Status Code should be 200
 And
     Response format should be "application/json"
 And
     There should be 200 todos
 And
     "quis eius est sint explicabo" should be one of the todos title
 And
     2, 7, and 9 should be among the userIds

 */
    @Test
    public void get04(){
        // To be able to reach spec Object we need to extend to related class(JasonPlaceHolderBaseUrl)

        spec.pathParam("first","todos");//With pathParam()method we add parameters to base URL
        // "first" represent the "todos" parameter in the URL
        // Set the expected data


        // Send the request and get response
       Response response= given(spec).get("{first}");
       response.prettyPrint();

       // Do Assertion
        response.then().
        statusCode(200).contentType(ContentType.JSON).
        body("id",hasSize(200),"title",hasItem("quis eius est sint explicabo"),"userId",hasItems(2,7,9));

   /*
        If response body returns as a list:
        We can check list size with hasSize() method
        We can check if list contains an item with hasItem() method
        We can check if list contains multiple items with hasItems() method
         */



    }



}



