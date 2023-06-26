package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
       /*
   Given
       https://restful-booker.herokuapp.com/booking/32
   When
       User send a GET request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response content type is "application/json"
   And
       Response body should be like;
              {
                            "firstname": "John",
                            "lastname": "Smith",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"

       */
@Test

    public void get06(){
    // Set the URL
     spec.pathParams("first","booking","second",204);
    // Set the Expected data
    // Send the request and get response
    Response response=given(spec).get("{first}/{second}");
    response.prettyPrint();
// Do Assertiion
    // WAY 1-Soft Assertion
    response
            .then()
            .statusCode(200)

            .contentType(JSON)
            .body("firstname", equalTo("John"),
                    "lastname", equalTo("Smith"),
                    "totalprice", equalTo(111),
                    "depositpaid", equalTo(true),
                    "bookingdates.checkin", equalTo("2018-01-01"),
                    "bookingdates.checkout", equalTo("2019-01-01"),
                    "additionalneeds", equalTo("Breakfast"));


     //WAY 2-We will use JsonPath class to extract the data outside the response body
    // Create JsonPath Object as will use it multiple times to get data from response body

    JsonPath jsonPath=response.jsonPath();
    // Get the data:
    // Hard Assertion
    assertEquals("John", jsonPath.getString("firstname"));
    assertEquals("Smith", jsonPath.getString("lastname"));
    assertEquals(111, jsonPath.getInt("totalprice"));
    assertTrue(jsonPath.getBoolean("depositpaid"));
    assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
    assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
    assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

    // We can not make Soft assertion with Junit framework with assertEquals()
    // We need to add and use Test engine framework

/*
This is new generation of Junit called Test NG(new generation) -Junit is old version it has more functionality
  <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.1.0</version>
        </dependency>
 */
    // SOFt Assertion with Test NG(new generation) engine soft assertion
    // TO do soft assertion follow these 3 steps
    // 1) Create Soft assert Object
          SoftAssert softAssert=new SoftAssert();

          // 2) Do Assertion by softAssert Object
    softAssert.assertEquals(jsonPath.getString("firstname"), "John", "firstname did NOT match");
    softAssert.assertEquals(jsonPath.getString("lastname"), "Smith", "lastname did NOT match");
    softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice did NOT match");
    softAssert.assertTrue(jsonPath.getBoolean("depositpaid"), "depositpaid did NOT match");
    softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin did NOT match");
    softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout did NOT match");
    softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast", "additionalneeds did NOT match");




    // 3) Use assertAll()method
    // After do assertion with softAssert if we do not do softAssert.assertAll aplication
    // will not have any assertion and will not work
    softAssert.assertAll();




























}


}
