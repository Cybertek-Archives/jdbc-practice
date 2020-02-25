package Day8_JsonSchema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

    @Test
    public void jsonSchemaValidationForSpartan(){
                //verifying that spartan id 5 is matching with expected Json Schema
               given().accept(ContentType.JSON)
                       .pathParam("id",15)
                       .when().get("/spartans/{id}").then()
                       .statusCode(200).body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));




    }

}
