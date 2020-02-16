package Day6_gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class JsonToJavaCollections {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void SpartanJsonToMap(){

        Response response = given()
                .accept(ContentType.JSON).pathParam("id",15)
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //convert json result to Java Map
        Map<String,Object> spartanMap = response.body().as(Map.class);

        System.out.println("spartanMap = " + spartanMap.toString());
        String name = (String) spartanMap.get("name");
        System.out.println("name = " + name);

        assertEquals(name,"Meta");
    }

    @Test
    public void allSpartansToList(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/spartans/");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        List<Map<String,Object>> spartanListOfMap = response.body().as(List.class);

        System.out.println("spartanListOfMap.size() = " + spartanListOfMap.size());
        System.out.println("spartanListOfMap.get(0) = " + spartanListOfMap.get(1));
        System.out.println("spartanListOfMap.get(0).get(\"name\") = " + spartanListOfMap.get(1).get("name"));

        Map<String, Object> ozzyMap = spartanListOfMap.get(1);
        System.out.println(ozzyMap.get("name"));
        String name = (String) spartanListOfMap.get(1).get("name");
        assertEquals(name,"Ozzy");
    }

    @Test
    public void regionJsonMap(){
        //request
        Response response = get("http://54.161.128.36:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);

        Map<String,Object> regionMap = response.body().as(Map.class);

        System.out.println(regionMap.get("limit"));
        System.out.println("regionMap.get(\"count\") = " + regionMap.get("count"));
        System.out.println("regionMap = " + regionMap.get("items"));

        List<Map<String,Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items");

        String region_name = (String) itemsList.get(0).get("region_name");
        System.out.println("region_name = " + region_name);


        //break until 11:25


    }



}
