package com.cydeo.day_09;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;



public class P04_JsonSchemaValidation extends SpartanTestBase {

    @DisplayName("GET /api/spartans/{id} to validate with JsonSchemaValidator")
    @Test
    public void test1() {

         given().accept(ContentType.JSON)
                .pathParam("id",43)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }

    @Test
    public void test2() {

        given().accept(ContentType.JSON)
                .pathParam("id",43)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json")));

    }

    @DisplayName("GET /api/spartans/search to validate with JsonSchemaValidator matchesJsonSchema")
    @Test
    public void test3() {

            given().accept(ContentType.JSON)
                    .when().get("/api/spartans/search")
                    .then().statusCode(200)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SearchSpartanSchema.json"));
    }

    /**
     *     Do schema validation for ALLSPARTANS and POST SINGLE SPARTAN
     *
     *     ALL SPARTANS
     *      1- Get all spartans by using /api/spartans
     *      2- Validate schema by using  JsonSchemaValidator
     *
     *
     *    POST SINGLE SPARTANS
     *       1- Post single spartan
     *       2- Validate schema by using  JsonSchemaValidator
     *
     */

}
