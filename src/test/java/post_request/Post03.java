package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerOkuAppTestData.bookingDateMapMethod;
import static test_data.HerOkuAppTestData.expectedDataMethod;

public class Post03 extends HerOkuAppBaseUrl {
    @Test
    public void post03(){
     // Set the URL
     spec.pathParam("first","booking");

     // Set the EExpected data
       Map<String,String> bookingdatesMap=bookingDateMapMethod("2021-09-09","2021-09-21");
       Map<String,Object>expectedData=expectedDataMethod("John","Doe",11111,true,bookingdatesMap,
               "Extra pillows please");
        System.out.println("expectedData" + expectedData);

         // Send Request and get Response
       Response response= given(spec).body(expectedData).post("{first}");
       response.prettyPrint();

       // Do Assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println("actualData" + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),   ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),   ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),((Map) actualData.get("booking")).get("additionalneeds"));


    }


}
      /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
             "firstname": "John",
             "lastname": "Doe",
             "totalprice": 11111,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-09-09",
                 "checkout": "2021-09-21"
              }
              "additionalneeds": "Extra pillows please"
           }
  When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 5315,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 11111,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2021-09-09",
                                                    "checkout": "2021-09-21"
                                                }
                                              "additionalneeds": "Extra pillows please"
                                            }



       */





