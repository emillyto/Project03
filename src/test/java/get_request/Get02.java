package get_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;

public class Get02 {

        /*
       Given
           https://restful-booker.herokuapp.com/booking/0
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Response body contains "Not Found"
       And
           Response body does not contain "TechProEd"
       And
           Server is "Cowboy"
    */

    @Test
    public void get02(){


//        i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

//        ii) Set the expected data

//        iii) Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

//        iv) Do Assertion
        response
                .then()
                .statusCode(404)//HTTP Status code should be 404
                .statusLine("HTTP/1.1 404 Not Found");//Status Line should be HTTP/1.1 404 Not Found


        //Response body contains "Not Found"
        String responseBody = response.asString();
        //With assertEqual() method we compare 2 parameters. If they are equal test will pass
        assertEquals("Not Found",responseBody);

        // Response body does not contain "TechProEd"
        // With assertNotEquals()method we compare 2 parameters.If they are no equal  test will pass
        // Or we can use assertFalse() method pass if the value between () is false,not equal
        assertFalse(responseBody.contains("TechProEd"));

        Assert.assertNotEquals("TechProEd",responseBody);

        // Server is "Cowboy"
        String server=response.header("Server");
        assertEquals("Cowboy",server);






    }
}
