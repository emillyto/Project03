package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationHerOkuApp.generateToken;

public class GmiBankBaseUrl {
    // This class is created to prevent repeated action in request like Base URL,
    // contain type,authorization etc.
   protected RequestSpecification spec;
    @Before// This annotation runs the method before each @test method in other class,
    //Then spec object will be assigned
    public void setUp(){

        spec= new RequestSpecBuilder().
                addHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJrX3R3YWluIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9NQU5BR0VSIiwiZXhwIjoxNjg4ODI2ODk1fQ." +
                        "Ud1h-pDjuEs7JHm1rine_OJQaQrdVSMGE327rjULkymCwTX4wSbDaqRHUEl899MQPy1RdoBiF39aYzDwr-qI3w").
                setContentType(ContentType.JSON).
                setBaseUri("https://www.gmibank.com/").
                build();

    }
}
