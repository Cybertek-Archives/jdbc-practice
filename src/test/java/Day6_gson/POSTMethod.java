package Day6_gson;

import com.google.gson.Gson;
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

public class POSTMethod {

    /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"MikeEU",
      "phone":8877445596
   }
    When user sends POST request to '/spartans/'
    Then status code 201
    And content type should be application/json
    And json payload/response should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void PostNewSpartan(){
        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body("{\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"name\": \"MikeEU\",\n" +
                        "  \"phone\": 5478783575\n" +
                        "}").when().post("/spartans");

        //response validations
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify response body
        String successMessage = response.path("success");
        System.out.println("successMessage = " + successMessage);
        assertEquals(successMessage,"A Spartan is Born!");

        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name,"MikeEU");
        assertEquals(gender,"Male");
        assertEquals(phone,5478783575l);
        //printing the id
        System.out.println(response.path("data.id").toString());


    }

    //send body with map

    @Test
    public void PostNewSpartanWithMap(){
        //we will only change the way of sending body part
        Map<String,Object> requestMap = new HashMap<>();
        //adding the values that we want to post
        requestMap.put("gender","Male");
        requestMap.put("name","MikeEUMAP");
        requestMap.put("phone",5478783575L);

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(requestMap).when().post("/spartans");

        //response validations
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify response body
        String successMessage = response.path("success");
        System.out.println("successMessage = " + successMessage);
        assertEquals(successMessage,"A Spartan is Born!");

        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name,"MikeEUMAP");
        assertEquals(gender,"Male");
        assertEquals(phone,5478783575l);
        //printing the id
        System.out.println(response.path("data.id").toString());
        int idFromPost = response.path("data.id");

        System.out.println("-----------END OF POST REQUEST--------------");
        //after post I want to send get request to new spartan
            given().pathParam("id",idFromPost)
                    .when().get("/spartans/{id}")
                    .then().assertThat().statusCode(200).log().all();



    }
    //send body with Pojo
    @Test
    public void PostNewSpartanWithPOJO(){
        //get name and gender from uinames
        //put some phone number then send request

       //we will only change the way of sending body part
        Spartan spartanEU=new Spartan();
        spartanEU.setGender("Female");
        spartanEU.setName("Jasmine");
        spartanEU.setPhone(4321232123L);

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(spartanEU).when().post("/spartans");

        //response validations
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify response body
        String successMessage = response.path("success");
        System.out.println("successMessage = " + successMessage);
        assertEquals(successMessage,"A Spartan is Born!");

        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name,"Jasmine");
        assertEquals(gender,"Female");
        assertEquals(phone,4321232123L);
        //printing the id
        System.out.println(response.path("data.id").toString());
        int idFromPost = response.path("data.id");

        System.out.println("-----------END OF POST REQUEST--------------");
        //after post I want to send get request to new spartan
        given().pathParam("id",idFromPost)
                .when().get("/spartans/{id}")
                .then().assertThat().statusCode(200).log().all();

        //break until 1:15

    }

}

