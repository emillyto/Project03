package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponse;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C01_CreateBooking extends HerOkuAppBaseUrl {
    public static Integer bookingId;
    //This is the bookingId of created booking, so we can use it in next classes.

    @Test
    public void post01() {
        // Create booking
        // Set the Url
        spec.pathParam("first", "booking");

        // Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2023-01-01", "2024-01-01");
        BookingPojo expectedData = new BookingPojo("John", "Doe", 100, true, bookingDatesPojo, "Extra Pillow");
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();


        // Do Assertion
        BookingResponse actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingResponse.class);
        System.out.println("actualData=" + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

       bookingId= actualData.getBookingid();
    }
}
     /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
          {
                "firstname" : "John",
                "lastname" : "Doe",
                "totalprice" : 100,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2023-01-01",
                    "checkout" : "2024-01-01"
                },
                "additionalneeds" : "Extra Pillow"
            }
   When
        Send post request
   Then
        Status code is 200
   And
        Response body is:
        {
            "bookingid": 3320,
            "booking": {
                "firstname": "John",
                "lastname": "Doe",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-01-01",
                    "checkout": "2024-01-01"
                },
                "additionalneeds": "Extra Pillow"

      */










