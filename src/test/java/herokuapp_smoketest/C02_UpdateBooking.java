package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02_UpdateBooking extends HerOkuAppBaseUrl {
    // WE will do Updating with PUT REQuest
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
      --- IN PUT REQUEST WE NEED 2 GIVENS- ONE IS FOR TH END POINT(URL) AND THE SECOND ONE IS THE BODY THAT
       IS WHY WE WILL
      AND KEYWORD
      AND
      {

            "firstname" : "Mark",
            "lastname" : "Twain",
            "totalprice" : 200,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2023-01-01",
                "checkout" : "2024-01-01"
            },
            "additionalneeds" : "Dinner"}

    When -THAT IS MEAN ACTION(SEND THE PUT REQUEST)
    Then
       Status Code is 200

       And

       Response Body is:

            "firstname" : "Mark",
            "lastname" : "Twain",
            "totalprice" : 200,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2023-01-01",
                "checkout" : "2024-01-01"
            },
            "additionalneeds" : "Dinner"}
             */
    @Test
    public void put01(){
        // Set the URL
        spec.pathParams("first","booking","second",bookingId);

        // Set the expected data-
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2023-01-01","2024-01-01");
        BookingPojo expectedData= new BookingPojo("Mark","Twain",200,true,
                bookingDatesPojo,"Dinner");
        System.out.println("expectedData=" + expectedData);

        // SEnd the request and get the response- we will send it as PUT request
        Response response = given(spec).
                body(expectedData).put("{first}/{second}");
        response.prettyPrint();

        // We need to run together with C01

        //Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
    }









    }

