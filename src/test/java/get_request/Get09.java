package get_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.expectedDataMap;

public class Get09 extends JsonBaseHolderUrl {
         /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
    @Test
    public void get09(){
        // Set the Url
        spec.pathParams("first","todos","second",2);

        // Set the expected data- to do Assertion we will create expected data
      Map<String, Object>expectedData= expectedDataMap(1,"quis ut nam facilis et officia qui",false );
        //expectedData.put("id",2); // If you need to assert the id as well we can put that field to
        //To prevent Hard Coding in Assertion we add the Headers("Via" and "Server")
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println("expectedData :" + expectedData);

        // Send the Request and Get Response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
       Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData" + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));

        // If we need to assert id as well:
       // assertEquals(expectedData.get("id"),actualData.get("id"));


    }

}
