package com.cydeo.day07.day04;

import com.cydeo.utilities.ZipCodeTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_ZipcodeTest extends ZipCodeTestBase {

   /*
   TASK 1
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
 post code is 22031
 country is United States
 country abbreviation is US
 place name is Fairfax
 state is Virginia
 latitude is 38.8604
    */
    @Test

    public void test1(){

        Response response=given().accept(ContentType.JSON)
                .and().pathParams("postal-code","22031")
                .when().get("/us/{postal-code}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertEquals("cloudflare", response.header("Server"));

        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        assertEquals("22031", jsonPath.getString("'post code'"));

        assertEquals("United States", jsonPath.get("'country'"));

        assertEquals("US", jsonPath.get("'country abbreviation'"));

        assertEquals("Fairfax", jsonPath.get("places[0].'place name'"));

        assertEquals("-77.2649", jsonPath.get("places[0].'longitude'"));

        assertEquals("Virginia", jsonPath.get("places[0].'state'"));

        assertEquals("VA", jsonPath.get("places[0].'state abbreviation'"));

        assertEquals("38.8604", jsonPath.get("places[0].'latitude'"));


    }

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


        Response response = given().accept(ContentType.JSON)
                .and().pathParams("postal-code", "50000")
                .when().get("/us/{postal-code}");

        response.prettyPrint();

        assertEquals(404, response.statusCode());

        assertEquals("application/json", response.contentType());
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
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("state", "va")
                .and().pathParams("city", "fairfax")
                .when().get("/us/{state}/{city}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertEquals("US", jsonPath.getString("'country abbreviation'"));

        assertEquals("United States", jsonPath.getString("'country'"));

        assertEquals("Fairfax", jsonPath.getString("'place name'"));

        assertTrue(jsonPath.getString("places[0].'place name'").contains("Fairfax"));

        assertTrue(jsonPath.getString("places[0].'post code'").startsWith("22"));
    }
}


