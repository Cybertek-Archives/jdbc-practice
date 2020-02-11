package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class SpartanTestWithPath {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");

    }
    /*
    Given accept type is json
    And path param id is 10
    When user sends a get request to "/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;char"
    And response payload values match the following:
        id is 10,
        name is "Lorenza",
        gender is "Female",
        phone is 3312820936
 */

    @Test
    public void getSpartanWithPath(){
        //request
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 10)
                .when().get("/spartans/{id}");

        //verify content type and status code
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //print response json body
        System.out.println("ID: "+response.body().path("id").toString());
        System.out.println("Name: "+response.path("name").toString());
        System.out.println("Gender: "+response.body().path("gender").toString());
        System.out.println("Phone: "+response.body().path("phone").toString());

        //save them
        int id = response.path("id");
        String firstName = response.body().path("name");
        String spartanGender = response.path("gender");
        long phoneNumber= response.path("phone");

        //do assertion
        assertEquals(id,10);
        assertEquals(firstName,"Lorenza");
        assertEquals(spartanGender,"Female");
        assertEquals(phoneNumber,3312820936L);

        //break until 4:05


    }
}
