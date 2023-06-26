package get_request;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestAndResponse {
    /*
    1) Postman is used for manual API Test
    2) We use Rest Assured Library for API Automation testing
    3) To type Automation script follow this steps
               ----- Understand the requirements
               ----- Type the case(type the steps called in testing TEST CASE)
               ----- To type test cases we use Gherking Languages
               ----- The keywords in Gherkin languages are:
    a) GIVEN  - it is used for pre-Condition (Endpoint URL)
    b) WHEN - It is used for action (send and get response-it is like Request
    c) THEN - it is used for Output ( Assertion)- DO this and That
    d) AND - it is used for multiple usage of keyword(first we will type WHEN ad after that no need to reped just
    use AND for not repetition
    e) Start to type Automation Script
              ----Set the URL
              ----Set the expected data-ehat you are espexted as data
              ---- Send the request and get a Responce
              ---- DO Assertion
     */

    // TASK 1
     /*
   Given
       https://restful-booker.herokuapp.com/booking/10
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/
    public static void main(String[] args) {
        //----Set the URL
        String url=" https://restful-booker.herokuapp.com/booking/10";
       // ----Set the expected data-that you are expected as data
        // We wil do that at the end

        //        ---- Send the request and get a Response

       Response response= given().get(url);//get() method returns Response to see response at the console use
        // prettyPrint() with response object
        //response.prettyPrint();// We wil get the response on the console

        //---- DO Assertion-to do Assertion we need to have Testing Frame Work
        // Like JUnit,Test NG,Cucumber etc.
        // How to get status code
       // HTTP Status Code should be 200-from task
        // All the data comes from API is inside the response.
        System.out.println(response.statusCode());

       // Content Type should be JSON
        // HOw to get content type
        System.out.println("Content Type: "+ response.contentType());// All of the information comes from  response

       // Status Line should be HTTP/1.1 200 OK

        // How to get Status LIne
        System.out.println( "Status Line : " +response.statusLine());

        // How to get Header
        System.out.println(response.header("Date"));

        // How to get Headers-that mean all inside in Header
        System.out.print("\n Headers: ");
        System.out.println(response.headers());

        // How tp get Time
        System.out.println("Time: "+response.time());







    }
}
