package post_request;

import base_urls.JsonBaseHolderUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06ObjectMapper_Pojo extends JsonBaseHolderUrl {
    @Test
    public void post06() throws JsonProcessingException {
        // Set the Url
        spec.pathParam("first","todos");

        // Set the expected data-as Pojo class and Object Mapper
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData=" + expectedData);

        // Send the request and get response
        Response response=given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        // Do Assertion-we will convert this Json into Pojo class by using Object Mapper
        JsonPlaceHolderPojo actualData= new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData" + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
    }

}

/*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2) { "userId": 55,
          "title": "Tidy your room",
          "completed": false
          }


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

