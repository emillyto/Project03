package put_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
    @Test
    public void put02(){
        // Set the Url
        spec.pathParams("first","update","second",21);

        // Set the expected data

        DummyRestApiDataPojo expectedData=new DummyRestApiDataPojo("Ali Can",111111,
                23,"Perfect image");
        System.out.println("expectedData" + expectedData);

        //Send the request and get the response-that meance PUT-request
       Response response= given(spec).body(expectedData).put("{first}/{second}");
       //We need to do serialization-that mean we have to declare contentType(JSON)otherwise we will see empty body in data
       response.prettyPrint();

       // Do Assertion
        DummyRestApiResponsePojo actualData=response.as(DummyRestApiResponsePojo.class);
        // We need to do deserialization
        // Before thet we need to do @IgnoreProperties in DummyReatApiDataPojo to get the data as null to avoid exseption
        // To get the data in body as expected we need to create another Pojo class,to assert the actual body
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());

        // If we need to assert full body do hard assertion
        assertEquals("success",actualData.getStatus());
        assertEquals("Successfully! Record has been updated.",actualData.getMessage());
    }
    /*
    ___ Type a Test case in Gerkin Language
    first key word used in Gerkin is

    Given -in given we need the Url and body.instead of using Given two times(one for URL
    and one for the body we used a keyword AND

    Given https://dummy.restapiexample.com/api/v1/update/21

    AND
      Request body -    "data": {
        "employee_name": "Ali Can",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image"
    }
    Send Put Request-PUT mean Action to send a pit request we need the keyword WHEN
    When - we need for send request
    Then- we used for Do Assertion for output
       Status code is 200
       And
       Response body should be like this
           "data": {
        "employee_name": "Ali Can",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image"
    }














     */





}
/*
   URL: https://dummy.restapiexample.com/api/v1/update/21
   HTTP Request Method: PUT Request
   Request body: {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }
   Test Case: Type by using Gherkin Language
   Assert:
            i) Status code is 200
            ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }
 */
