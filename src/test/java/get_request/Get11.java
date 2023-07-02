package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends HerOkuAppBaseUrl {
    @Test
    public void get11(){
        // Set the Url
        spec.pathParams("first,","booking","second",3622);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Jane","Doe",111,true,bookingDatesPojo,
                "Extra pillows please");
        System.out.println("expectedData " + expectedData);

        // Send the request and get the response
        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        // Do Assertion
       BookingPojo actualDta= response.as(BookingPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualDta.getFirstname());
        assertEquals(expectedData.getLastname(),actualDta.getLastname());
        assertEquals(expectedData.getTotalprice(),actualDta.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualDta.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualDta.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualDta.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualDta.getAdditionalneeds());




    }

}
/*
   Given
       https://restful-booker.herokuapp.com/booking/49
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
