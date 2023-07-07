package Patch_Request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.expectedDataMap;

public class Patch01 extends JsonBaseHolderUrl {
    @Test
    public void patch01(){
        // Set the URL
        spec.pathParams("first","todos","second",198);

        // Send the expected
       Map<String,Object>expectedData= JsonPlaceHolderTestData.expectedDataMap(null,"Read the books",null);
        System.out.println("expectedData =" + expectedData);

        // Send the request and get the response
       Response response= given(spec).body(expectedData).patch("{first}/{second}");
       response.prettyPrint();

       // Do Assertion
        Map<String,Object>actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData=" + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("title"),actualData.get("title"));
        // If you want to assert whole body we need to do hard assertion
        assertEquals(10,actualData.get("userId"));
        assertEquals(true,actualData.get("completed"));


    }

  /*
   Given
       1) https://jsonplaceholder.typicode.com/todos/198
       2) {
            "title": "Read the books"
          }
   When
        I send PATCH Request to the Url
   Then
         Status code is 200
         And response body is like   {
                                   "userId": 10,
                                   "title": "Read the books",
                                   "completed": true,
                                   "id": 198
                                  }
*/

}
