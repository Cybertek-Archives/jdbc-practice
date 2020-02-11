package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTest {

    String spartanAllUrl = "http://54.161.128.36:8000/api/spartans";

    @Test
    public void viewAllSpartans(){
        Response response = RestAssured.get(spartanAllUrl);

        //print the status code
        System.out.println(response.statusCode());

        //print response body
        //System.out.println(response.body().asString());

        //pretty print
        System.out.println(response.body().prettyPrint());

    }

    /* when user send GET request to /api/spartans end point
        Then status code must be 200
        And body should contains Orion
     */

    @Test
    public void viewSpartanTest1(){
        Response response = RestAssured.get(spartanAllUrl);

        //verify status code is 200
        Assert.assertEquals(response.statusCode(),200);
        //verify body includes Orion
        Assert.assertTrue(response.body().asString().contains("Orion"));

    }

     /* Given accept type applicaton/json
        when user send GET request to /api/spartans end point
        Then status code must be 200
        And Response Content type must be Json
        And body should contains Orion
     */

     @Test
    public void viewSpartanTest2(){
         Response response = given().accept(ContentType.JSON)
                            .when().get(spartanAllUrl);
         //verify status code
         Assert.assertEquals(response.statusCode(),200);
          //verify response content type is json
         Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");



     }

        //  /* Given accept type application/xml
    //        when user send GET request to /api/spartans end point
    //        Then status code must be 200
    //        And Response Content type must be xml
    //        And body should contains Orion
    //     */

    @Test
    public void viewSpartanTest3(){
        Response response = given().accept(ContentType.XML)
                .when().get(spartanAllUrl);
        //verify status code
        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.contentType(),"application/xml;charset=UTF-8");
        //body contains Orion
        assertTrue(response.body().asString().contains("Orion"));
    }

    //  /* Given accept type application/xml
    //        when user send GET request to /api/spartans end point
    //        Then status code must be 200
    //        And Response Content type must be xml
    //     */

    @Test
    public void viewSpartanTest4(){
                //request starts here
                given().accept(ContentType.XML)
                .when().get(spartanAllUrl).
                then().statusCode(200)
                .and().contentType("application/xml;charset=UTF-8");

    }

    /*
        Given the accept type Json
        When I send get request to /api/spartans/3
        Then status code must be 200
        And Content type should be json
        and body should contains Fidole
     */

    @Test
    public void getOneSpartan(){
        Response response = given().accept(ContentType.JSON).when().get(spartanAllUrl + "/3");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Fidole"));

    }


    /*
        Given the accept type XML
        When I send get request to /api/spartans/3
        Then status code must be 406
     */

    @Test
    public void negativeContentType(){

        Response response = given().accept(ContentType.XML)
                .when().get(spartanAllUrl + "/3");

        assertEquals(response.getStatusCode(),406);

    }

    /*Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta”
    */







}
