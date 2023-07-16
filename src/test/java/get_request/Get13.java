package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get13 extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        And
            The female users are less than or equals to male users
    */
    @Test
    public void get13(){
        // Set the Url
        spec.pathParam("first","users");

        // Set the expected data

        // Do Assertion
        // We will get the request and get the response
       Response response= given(spec).get("{first}");
       response.prettyPrint();

       // Do Assertion
        response.then().statusCode(200).body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current",
                equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data", hasSize(10),
                "data.status", hasItem("active"),
                "data.name", hasItems("Chakravartee Gandhi MD", "Veda Dutta", "Sachin Bhattacharya"));

        //The female users are less than or equals to male users
        List<String> genders = response.jsonPath().getList("data.gender");
        System.out.println("genders = " + genders);

        //1st Way: By using foreach loop
        int numOfFemales = 0;
        for (String w : genders) {

            if (w.equalsIgnoreCase("female")) {
                numOfFemales++;
            }

        }
        System.out.println("numOfFemales = " + numOfFemales);

        assertTrue(numOfFemales <= genders.size() - numOfFemales);


        //2nd Way: By using groovy language
        int numberOfFemales = response.jsonPath().getList("data.findAll{it.gender=='female'}").size();
        int numberOfMales = response.jsonPath().getList("data.findAll{it.gender=='male'}").size();
        System.out.println("numberOfFemales = " + numberOfFemales);
        System.out.println("numberOfMales = " + numberOfMales);

        assertTrue(numberOfFemales <= numberOfMales);

    }

    }


