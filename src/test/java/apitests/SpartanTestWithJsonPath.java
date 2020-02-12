package apitests;
import com.sun.source.tree.AssertTree;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class SpartanTestWithJsonPath {

    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");

    }
    /*
   Given accept type is json
   And path param spartan id is 11
   When user sends a get request to /spartans/{id}
   Then status code is 200
   And content type is Json
   And  "id": 11,
        "name": "Nona",
        "gender": "Female",
        "phone": 7959094216
    */
    @Test
    public void verifyoneSpartanWithJsonPath(){
        //request part
        Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 11)
                .when().get("/spartans/{id}");

        //response
        //status code, header, body
        //verify status code
            assertEquals(response.statusCode(),200);
        //header
            //verify content type
            assertEquals(response.getHeader("Content-Type"),"application/json;charset=UTF-8");
            //verify Transfer-Encoding is exist in response headers
            assertTrue(response.headers().hasHeaderWithName("Transfer-Encoding"));
        //body
            //verify id and name using path() method
            int id = response.path("id");
            assertEquals(id,11);
            String name = response.path("name");
            assertEquals(name,"Nona");

        //NEW TOPIC HERE
            //verify full body with jsonPath
        //assign response body to jsonpath object
        JsonPath json = response.jsonPath();

        //verify body
        int idJson = json.getInt("id");
        String nameJson = json.getString("name");
        String genderJson = json.getString("gender");
        long phoneJson = json.getLong("phone");

        System.out.println("idJson = " + idJson);
        System.out.println("firstName = " + nameJson);
        System.out.println("genderJson = " + genderJson);
        System.out.println("phone = " + phoneJson);
        //verify full body
        assertEquals(idJson,11);
        assertEquals(nameJson,"Nona");
        assertEquals(genderJson,"Female");
        assertEquals(phoneJson,7959094216L);
    }



}
