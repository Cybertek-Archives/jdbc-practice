package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersApiTest
{
    /*
    given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */

    @Test
    public void singleSpartanbyChaining(){

         given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://54.161.128.36:8000/api/spartans/{id}")
                 .then().assertThat().statusCode(200)
                 .and().assertThat().contentType("application/json;charset=UTF-8")
                 .and().assertThat().body("id",equalTo(15),
                        "name",equalTo("Meta")
                                                ,"gender",equalTo("Female"),
                                                    "phone",equalTo(1938695106));


    }


    @Test
    public void teacherData(){
        given().accept(ContentType.JSON).and()
                .pathParam("name","Madham").and()
                .when().get("http://api.cybertektraining.com/teacher/name/{name}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8")
                .and().body("teachers.firstName[0]",equalTo("Madham"),
                "teachers.lastName[0]",equalTo("Mask"),
                "teachers.emailAddress[0]",equalTo("jackma@gmail.com"));


    }

    //teacher/department/{name} -->Computer
    //verify status code,content type
    //verify firstNames includes Madham,Ruslan,Alihan

    @Test
    public void teachersAllDataWithHamcrest(){
            given().accept(ContentType.JSON).pathParam("name","Computer")
                    .when().get("http://api.cybertektraining.com/teacher/department/{name}")
                    .then().assertThat().statusCode(200).and().contentType("application/json;charset=UTF-8")
                    .and().body("teachers.firstName",hasItems("Madham","Ruslan","Alihan"));
    }

    //break until 4:00
    //ozzy will come ta a break
    


}
