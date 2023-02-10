package com.cydeo.day05;

import com.cydeo.utilities.HRTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import com.cydeo.utilities.ZipCodeTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class P05_ZipcodeTest_HamCrest extends ZipCodeTestBase {


    @Test

    public void test2() {
        /*
        TASK 2
Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/json
         */


                given().accept(ContentType.JSON)
                .and().pathParams("postal-code", "50000")
                .when().get("/us/{postal-code}")
                        .then()
                        .statusCode(404)
                        .contentType("application/json");


    }

    @Test

    public void test3() {
        /*
        TASK 3
Given Accept application/json
And path state is va
And path city is fairfax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
 country abbreviation is US
 country United States
 place name Fairfax
 each places must contains fairfax as a value
 each post code must start with 22
         */
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().pathParams("state", "va")
                .and().pathParams("city", "fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(200)
                .contentType("application/json")
                .body("'country abbreviation'", equalTo("US"))
                .body("country", equalTo("United States"))
                .body("'place name'", equalTo("Fairfax")).extract().jsonPath();


        List<Map<String, Object>> places = jsonPath.getList("places");

        for (Map<String, Object> eachPlaces : places) {
            System.out.println("eachPlaces = " + eachPlaces);

        }
        System.out.println("places = " + places);

        for (int i = 0; i < places.size(); i++) {
            assertTrue(places.get(i).get("place name").toString().contains("Fairfax"));
            assertTrue(places.get(i).get("post code").toString().contains("22"));
        }



    }
}
