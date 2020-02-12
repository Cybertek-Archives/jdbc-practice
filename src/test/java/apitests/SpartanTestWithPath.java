package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import java.util.HashMap;
import java.util.List;
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

    }

    @Test
    public void getAllSpartansWithPath(){
        //request
        Response response = get("/spartans/");

        assertEquals(response.statusCode(),200);

        //print the first id
        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        //print first name from the all spartans
        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        //get last name
        String lastFirstName = response.path("name[-1]");
        System.out.println("lastName = " + lastFirstName);

        //get all firstnames and print out
        List<String> names = response.path("name");
        System.out.println("names = " + names.size());
        System.out.println("names = " + names);

        //print all phone numbers one by one
        List<Object> phoneNums =response.path("phone");
        for (Object nums: phoneNums) {
            System.out.println(nums);}


    }



}
