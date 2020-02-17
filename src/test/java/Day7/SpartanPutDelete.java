package Day7;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanPutDelete {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void updatingSpartan(){
        Map<String,Object> putMap = new HashMap<>();
        putMap.put("name","putname");
        putMap.put("gender","Female");
        putMap.put("phone",6986436734L);

            given().pathParam("id",50)
                    .and().contentType(ContentType.JSON)
                    .and().body(putMap)
                    .when().put("/spartans/{id}").then()
                    .assertThat().statusCode(204);
    }

    @Test

    public void DeleteASpartan(){
        Random rn=new Random();
        int idToDelete=rn.nextInt(400)+2;
        System.out.println("idToDelete = " + idToDelete);
        given().pathParam("id",idToDelete)
                .when().delete("/spartans/{id}").then()
                .assertThat().statusCode(204);



    }

}
