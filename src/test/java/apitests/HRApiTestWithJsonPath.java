package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HRApiTestWithJsonPath {

    @BeforeClass
    public void setUpClass(){
        baseURI = ConfigurationReader.get("hrapi.uri");
    }

    @Test
    public void countriesWithJsonPath(){
        //request
        Response response = get("/countries");

        //put response body to JsonPath object
        JsonPath jsonData = response.jsonPath();

        //read second country name -Australia
        String secondCountryName = jsonData.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> countryIds=jsonData.getList("items.country_id");
        System.out.println("countryIds.size = " + countryIds.size());
        System.out.println("countryIds = " + countryIds);

        //get countryNames off all countries in region id 2
        List<String> countryNamewithRegion2 = jsonData.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNamewithRegion2 = " + countryNamewithRegion2);
    }

    @Test
    public void findAllEmployeesExample(){
        //request
        Response response = given().queryParam("limit",150).get("/employees");

        //put response body to JsonPath object
        JsonPath jsonData = response.jsonPath();

        //get me all first_name of employees who is working as IT_PROG
        List<String> firstnames = jsonData.getList("items.findAll {it.job_id ==\"IT_PROG\"}.first_name");
        System.out.println("firstnames = " + firstnames);

        //get me all first_name of employees who is making more than 5k
        List<String> firstnames2 = jsonData.getList("items.findAll {it.salary > 5000}.first_name");
        System.out.println("firstnames2 = " + firstnames2);

    }

}
