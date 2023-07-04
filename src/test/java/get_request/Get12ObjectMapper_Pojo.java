package get_request;

import base_urls.JsonBaseHolderUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get12ObjectMapper_Pojo extends JsonBaseHolderUrl {
     /*
   Given
       https://jsonplaceholder.typicode.com/todos/198
   When
        I send GET Request to the URL
    Then
        Status code is 200
        And response body is like { "userId": 10,"id": 198,"title": "quis eius est sint explicabo","completed": true }
*/
    @Test
    public void get12(){
        // Set the URL
        spec.pathParams("first","todos","second",198);

        // Set the expected data
        String stringJson="{ \"userId\": 10,\"id\": 198,\"title\": \"quis eius est sint explicabo\",\"completed\": true }";
       JsonPlaceHolderPojo expectedData= convertJsonToJava("", JsonPlaceHolderPojo.class);
        System.out.println("expectedData" + expectedData);

        // Send the request and get response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();


        //Do assertion
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getUserId(),actualData.getUserId());

    }



}


}
