package PetStoreSmokeTest;

import base_urls.PetBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Tags;
import pojos.Category;
import pojos.PetPojo;
import pojos.Tagos;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreatePet extends PetBaseUrl {
    /*
    Given
           https://petstore.swagger.io/v2/pet/3467889
       And
           {
            "id": 3467889,
            "category": {
                "id": 0,
                "name": "Bird"
            },
            "name": "Tweety",
            "photoUrls": [
                "string"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "string"
                }
            ],
            "status": "available"
           }
    When
    SEnd the Post REquest
    Then
      Then
        Status code is 200
    And
        Response body is:
                {
        "id": 3465589,
        "category": {
            "id": 0,
            "name": "Bird"
        },
        "name": "Tweety",
        "p": [
            "string"
        ],
        "tags": [
            {
                "id": 0,
                "name": "string"
            }
        ],
        "status": "available"
        }
     */

    @Test

    public void post01() {
        // Set the Url

        spec.pathParam("first", "pet");

        // Set the expected data

        //Set the expected data
        Category category = new Category(0, "Bird");
        Tags tags = (Tags) new Tagos(0, "string");
        List<Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);
        List<String> photoUrlsList = new ArrayList<>();
        photoUrlsList.add("string");
        PetPojo expectedData = new PetPojo(3465589, category, "Tweety", photoUrlsList,tagsList,
                "available");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        PetPojo actualData = convertJsonToJava(response.asString(), PetPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(category.getId(), actualData.getCategory().getId());
        assertEquals(category.getName(), actualData.getCategory().getName());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getPhotoUrls().get(0), actualData.getPhotoUrls().get(0));
        assertEquals(expectedData.getTags().get(0).getId(), actualData.getTags().get(0).getId());
        assertEquals(expectedData.getTags().get(0).getName(), actualData.getTags().get(0).getName());
        assertEquals(expectedData.getStatus(), actualData.getStatus());

    }

    }



