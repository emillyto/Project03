package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationHerOkuApp.generateToken;


public class HerOkuAppBaseUrl {
    // This class is created to prevent repeated action in request like Base URL,
    // contain type,authorization etc.
   protected RequestSpecification spec;
    @Before// This annotation runs the method before each @test method in other class,
    //Then spec object will be assigned
    public void setUp(){

        spec= new RequestSpecBuilder().
                addHeader("Cookie","token="+generateToken()).
                setContentType(ContentType.JSON).
                setBaseUri("https://restful-booker.herokuapp.com").build();

    }
}
