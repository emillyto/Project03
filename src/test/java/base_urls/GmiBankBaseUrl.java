package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GmiBankBaseUrl {
    // This class is created to prevent repeated action in request like Base URL,
    // contain type,authorization etc.
   protected RequestSpecification spec;
    @Before// This annotation runs the method before each @test method in other class,
    //Then spec object will be assigned
    public void setUp(){

        spec= new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("https://www.gmibank.com/").
                build();

    }
}
