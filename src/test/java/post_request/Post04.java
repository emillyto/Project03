package post_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends JsonBaseHolderUrl {

    @Test
    public void post04(){
        // Set the Url
        spec.pathParam("first","todos");

        // Set the expected Data -Will do it by POJO CLass(Plain Java Object)
        // To do that we will create an Object for JsonPlaceHolderPojo and we will be
        //able to put a different fields as a data
       JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData =" + expectedData);

        // Send the Request and Get the Response
       Response response= given(spec).body(expectedData).post("{first}");
       response.prettyPrint();

       // Do Assertion
       JsonPlaceHolderPojo actualData= response.as(JsonPlaceHolderPojo.class);// That is Deserialization
        System.out.println("actualData= " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());






    }


}
 /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
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

