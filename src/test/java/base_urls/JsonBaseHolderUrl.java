package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonBaseHolderUrl {
    // This class is created to prevent repeated action in request like Base URL,
    // contain type,authorization etc.
   protected RequestSpecification spec;

    @Before//@Before annotation will run this method before each @Test methods. So, spec object will not be Null.
    public void setUp() {
        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

    }

