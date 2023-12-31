package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponse;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends HerOkuAppBaseUrl {
    @Test
    public void post05() {
        //Set the url
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo expectedData = new BookingPojo("John", "Doe", 999, true, bookingDatesPojo, "Breakfast");

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponse actualData = response.as(BookingResponse.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

    }
}
 /*
   Given
    1)  https://restful-booker.herokuapp.com/booking
    2)   {
          "firstname": "John",
          "lastname": "Doe",
          "totalprice": 999,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2021-09-21",
              "checkout": "2021-12-21"
           },
           "additionalneeds": "Breakfast"
          }
  When
       I send POST Request to the URL
   Then
       Status code is 200
   And
       Response body is like {
                               "bookingid": 16,
                               "booking" :{
                                  "firstname": "John",
                                  "lastname": "Doe",
                                  "totalprice": 999,
                                  "depositpaid": true,
                                  "bookingdates": {
                                      "checkin": "2021-09-21",
                                      "checkout": "2021-12-21"
                                  },
                                  "additionalneeds": "Breakfast"
                               }
                            }
*/
