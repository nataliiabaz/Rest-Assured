package com.cydeo.day06;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class My_Practice extends SpartanTestBase {

     /*
    Given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200*/

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}").prettyPeek().
                then()
                .statusCode(200).extract().response();


        Map<String, Object> spartan = response.body().as(Map.class);

        System.out.println("spartan = " + spartan);

        System.out.println("spartan.get(\"name\") = " + spartan.get("name"));

        System.out.println(spartan.get("id"));


    }

    @Test

    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans/").prettyPeek().
                then()
                .statusCode(200).extract().response();

        List<Map<String, Object>> allSartans = response.body().as(List.class);

        System.out.println("allSartans = " + allSartans);

        for (Map<String, Object> eachSartan : allSartans) {
            System.out.println("eachSartan = " + eachSartan);

        }
    }
        @Test

        public void test3 () {

            JsonPath jsonPath = given().accept(ContentType.JSON)
                    .when().get("/api/spartans/").prettyPeek().
                    then()
                    .statusCode(200).extract().jsonPath();

            List<Map<String, Object>> allSartans = jsonPath.getList("");

            System.out.println("allSartans = " + allSartans);

        }

    }


