package com.cydeo.day07.day04;

import com.cydeo.utilities.CydeoTrainingTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_CydeoTraining extends CydeoTrainingTestBase {


   @Test
    public void test1(){

       Response response=given().log().all().accept(ContentType.JSON)
               .and()
               .pathParam("id", 2).and().when().get("/student/{id}");

       response.prettyPrint();

       assertEquals(200, response.statusCode());

       assertEquals("application/json;charset=UTF-8", response.contentType());

       assertTrue(response.headers().hasHeaderWithName("Date"));

       assertEquals("envoy",response.header("Server"));

       /*
         firstName Mark               ---> students[0].firstName
         batch 13                     ---> students[0].batch
         major math                   ---> students[0].major
         emailAddress mark@email.com  ---> students[0].contact.emailAddress
         companyName Cydeo            ---> students[0].company.companyName
         street 777 5th Ave           ---> students[0].company.address.street
         zipCode 33222                ---> students[0].company.address.zipCode
         */

       // Create JSON PATH OBJECT
       JsonPath jsonPath = response.jsonPath();

       assertEquals("Mark",jsonPath.getString("students[0].firstName"));

       assertEquals(13,jsonPath.getInt("students[0].batch"));

       assertEquals("math",jsonPath.getString("students[0].major"));

       assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

       assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));

       assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));

       assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));


   }

   /*

    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */

   @Test
   public void test2(){


   }



}
