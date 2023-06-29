package post_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;

public class Post04 extends JsonBaseHolderUrl {

    @Test
    public void post04(){
        // Set the Url
        spec.pathParam("first","todos");

        // Set the expected Data -Will do it by POJO CLass(Plain Java Object)
        // To do that we will create an Object for JsonPlaceHolderPojo and we will be
        //able to put a diferent fields as a data
       JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData =" + expectedData);

        // Send the Request and Get the Response
       Response response= given(spec).body(expectedData).post("{first}");
       response.prettyPrint();

       // Do Assertion



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

