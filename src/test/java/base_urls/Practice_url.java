package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class Practice_url {
    protected RequestSpecification spec;
    @Before
    public void setUp(){
       spec= new RequestSpecBuilder().setBaseUri("https://reqres.in/api/").build();
    }
}
