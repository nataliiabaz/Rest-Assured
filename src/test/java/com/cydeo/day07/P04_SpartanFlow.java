package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_SpartanFlow extends SpartanTestBase {
    int globalId;



    @Order(value=1)
    @DisplayName("Post spartan")
    @Test
    public void test1() {
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","Nataly");
        requestBody.put("gender","Female");
        requestBody.put("phone","1234567890");

        String expectedMessage="A Spartan is Born!";

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()// API send me response in JSON format
                .contentType(ContentType.JSON) // API I am sending body in JSON format
                .body(requestBody).
                when().post("/api/spartans").prettyPeek().
                then().statusCode(201)
                .contentType("application/json").extract().jsonPath();


        // What if I want to get id
        globalId = jsonPath.getInt("data.id");
        System.out.println("globalId = " + globalId);
    }
    @Order(2)
    @DisplayName("Get spartan")
    @Test
    public void test2() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                        .when().pathParam("id",104)
                        .and().get("/api/spartans/{id}").prettyPeek()
                        .then().extract().jsonPath();


        assertEquals("Nataly",jsonPath.getString("name"));
    }
    @Order (value = 3)
    @DisplayName (" PUT Spartan with spartanID /api/spartans/{id}")
    @Test
    public void test3() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name","Nataly");
        requestBody.put("gender","Female");
        requestBody.put("phone","1234567890");

        JsonPath jsonPath = given().log().body()
                .contentType(ContentType.JSON)
                .pathParam("id", 104)
                .body(requestBody)
                .when().put("/api/spartans/{id}").prettyPeek()
                .then().statusCode(204).extract().jsonPath();
    }

    @Order (value = 4)
    @DisplayName ("GET Spartan with spartanID /api/spartans/{id}")
    @Test
    public void test4() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id", 104)
                .and().get("/api/spartans/{id}")
                .then().statusCode(200).extract().jsonPath();


    }
}
