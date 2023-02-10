package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Homework01 {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://3.90.208.45:1000/ords/hr";

    }
//    Q1:
//            - Given accept type is Json
//- When users sends request to /countries/US
//- Then status code is 200
//- And Content - Type is application/json
//- And response contains United States of America

        @Test
    public void test1(){

            Response response = given()
                    .accept(ContentType.JSON)
                    .when()
                    .get("/countries/US");

            response.prettyPrint();

            System.out.println("response.statusCode() = " + response.statusCode());

            Assertions.assertEquals(200,response.statusCode());

            Assertions.assertEquals("application/json", response.header("Content-Type"));

            Assertions.assertTrue(response.body().asString().contains("United States of America"));

        }

//    Q2:
//            - Given accept type is Json
//- When users sends request to /employees/1
//            - Then status code is 404

    @Test

    public void test2(){

        Response response=given().accept(ContentType.JSON).when().get("/employees/1");

        Assertions.assertEquals(404,response.statusCode());
    }

//    Q3:
//            - Given accept type is Json
//- When users sends request to /regions/1
//            - Then status code is 200
//            - And Content - Type is application/json
//- And response contains Europe
//- And header should contains Date
//- And Transfer-Encoding should be chunked

        @Test
    public void test3(){

        Response response=given().accept(ContentType.JSON).when().get("/regions/1");

        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals("application/json", response.header("Content-Type"));

        Assertions.assertTrue(response.body().asString().contains("Europe"));

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        Assertions.assertEquals("chunked", response.header("Transfer-Encoding"));
        }
}
