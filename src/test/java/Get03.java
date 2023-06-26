import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 {
      /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
     */

    @Test
    public void get03() {
        //Set the url
        String url = "https://jsonplaceholder.typicode.com/todos/23";

        //Set the expected data

        //Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

        //Do assertion
        // THIS IS HARD ASSERTION
        //  Java will stop the execution and will not check other assertion  after
        // the failure and will not show us where the other failures are
        // because in Hard Assertion we use body()method for each assertion
        response
                .then()
                .statusCode(200)
                .contentType("application/json")//"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(2));

        // SOFT ASSERTION
        // Java will execute all Assertions even first one failier that way we use just ine body() and put
        // all assertion in it
        response.then().statusCode(200).contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),"userId", equalTo(2));




    }



}
