package delete_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonBaseHolderUrl {
     /*
     Given
         https://jsonplaceholder.typicode.com/todos/198
     When
   I send DELETE Request to the Url
Then
   Status code is 200
   And Response body is { }
  */

    @Test
    public void delete01(){
        // WAY 1-
        // Set the URL
        spec.pathParams("first","todos","second",198);

        // Set the expected data
        Map<String,String> expectedData=new HashMap<>();

        //Send the request and get the response
       Response response= given(spec).delete("{first}/{second}");
       response.prettyPrint();

       // Do Assertion
       Map<String,String>actualData= ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
       assertEquals(200,response.statusCode());
       assertEquals(expectedData,actualData);

       // WAY -2-assertEquals will return boolean and will pass if body is empty
        assertTrue(actualData.isEmpty());

        // Way 3-it will pass if body is empty again
        assertEquals(0,actualData.size());

        // When you do DELETE request we must be careful.Do not delete the data thats belongs to others!
        // First Create a data for your self and deleted then






    }

}
