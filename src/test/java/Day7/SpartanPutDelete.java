package Day7;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanPutDelete {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = ConfigurationReader.get("spartanapi.uri");
    }

}
