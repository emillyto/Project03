package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerOkuAppTestData.expectedDataMethod;

@SuppressWarnings("ALL")
public class Get10 extends HerOkuAppBaseUrl {
    // Nested Map
      /*
        Given
            https://restful-booker.herokuapp.com/booking/34
        When
            I send GET Request to the url
        Then
            Response body should be like that;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */
    @Test
    public void get10(){
        // Set the URL
        spec.pathParams("first","booking","second",631);

        // Set the expected data-Will be prepared the Map
        // Get the Map for Inner Map  "checkin": "2018-01-01",
        //                            "checkout": "2019-01-01"
        Map<String,String>bookingDateMap=new HashMap<>();
        bookingDateMap.put("checkin","2018-01-01");
        bookingDateMap.put("checkout","2019-01-01");
        // Prepare the Outer Map-that is outer map in our task "firstname": "Jane",
        //                    "lastname": "Doe",
        //                    "totalprice": 111,
        //                    "depositpaid": true,
        //                    "bookingdates":
        Map<String,Object>expectedData=new HashMap<>();
        expectedData.put("firstname","Jane");
        expectedData.put("lastname","Doe");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDateMap);
        expectedData.put("additionalneeds","Extra pillows please");
        System.out.println("expectedData =" + expectedData);

        // Send the Request and Response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        //Actual data is Json and expected  data is Map that is way wee need
        // to convert Json to Map as we need the two data to be in same format
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData" + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));

        // Since all values are inside the Object container we need to do type casting to use it
        // as original data

        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));



    }

    // Reccomended Way
    @Test
    public void get10b(){
        // Set the URL
        spec.pathParams("first","booking","second",45);

        // Set the expected data-Will be prepared the Map
        // Get the Map for Inner Map  "checkin": "2018-01-01",
        //                            "checkout": "2019-01-01"
        Map<String,String>bookingDateMap= HerOkuAppTestData.bookingDateMapMethod("2018-01-01","2019-01-01");

        // Prepare the Outer Map-that is outer map in our task "firstname": "Jane",
        //                    "lastname": "Doe",
        //                    "totalprice": 111,
        //                    "depositpaid": true,
        //                    "bookingdates":
        Map<String,Object>expectedData=expectedDataMethod("Jane","Doe",
                111,true,bookingDateMap,"Extra pillows please");


        System.out.println("expectedData =" + expectedData);

        // Send the Request and Response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        //Actual data is Json and expected  data is Map that is way wee need
        // to convert Json to Map as we need the two data to be in same format
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData" + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));

        // Since all values are inside the Object container we need to do type casting to use it
        // as original data

        assertEquals(bookingDateMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));




    }
}
