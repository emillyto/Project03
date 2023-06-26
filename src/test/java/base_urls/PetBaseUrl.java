package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetBaseUrl {
    // This class is created to prevent repeated action in request like Base URL,
    // contain type,authorization etc.
   protected RequestSpecification spec;
    @Before// This annotation runs the method before each @test method in other class,
    //Then spec object will be assigned
    public void setUp(){

        spec= new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();

    }
}
