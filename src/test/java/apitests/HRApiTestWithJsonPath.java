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

    }

}
