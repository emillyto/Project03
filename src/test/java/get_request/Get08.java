package get_request;

import base_urls.JsonBaseHolderUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonBaseHolderUrl {
    /*
   Given
          https://jsonplaceholder.typicode.com/todos
   When
        I send GET Request to the URL
   Then
        1)Status code is 200
        2)Print all ids greater than 190 on the console
          Assert that there are 10 ids greater than 190
        3)Print all userIds whose ids are less than 5 on the console
          Assert that the number of userIds whose ids are less than 5 is 4
        4)Print all titles whose ids are less than 5
          Assert that "delectus aut autem" is one of the titles whose id is less than 5
*/
    @Test
    public void get08(){
        // Set the URL
        spec.pathParam("first","todos");

        // SEt the expected data

        // Send the request and get response
       Response response= given(spec).get("{first}");
       response.prettyPrint();

       // Do Assertion
        assertEquals(200,response.statusCode());

        // 2)Print all ids greater than 190 on the console
        // WAY 1- By using While Loop

        JsonPath jsonPath=response.jsonPath();
        List<Integer> idList=jsonPath.getList("id");
       // System.out.println("idList=" + idList);
        List<Integer> idsGreaterThan190=new ArrayList<>();
        for(int w:idList){
            if (w>190){
                idsGreaterThan190.add(w);

            }


        }


        //   Assert that there are 10 ids greater than 190
        System.out.println("idsGreaterThan190=" + idsGreaterThan190);
        assertEquals(10,idsGreaterThan190.size());


        // WAY 2-By using Groovy Language-RECOMMENDED!!!// If we have a list with Json elements we can use Groovy
        // language
        System.out.println(jsonPath.getList("findAll{it.id>190}.id"));


        List<Integer>idsLessThan5Groovy=jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("idsLessThan5Groovy = " + idsLessThan5Groovy);


       List<String>titleList= jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titleList" + titleList);

        assertTrue(titleList.contains("delectus aut autem"));























    }
}
