package com.cydeo.day15;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_SpartanMock extends SpartanTestBase {
   @BeforeAll
   public static void init(){
       baseURI="https://4c678f45-0fdd-416e-b62d-bc4b94705f56.mock.pstmn.io";

   }



    @DisplayName("GET /api/hello")
    @Test
    public void test() {

       Response response= given().log().all()
                .accept(ContentType.JSON)
                .when().get("api/hello").prettyPeek()
                .then().statusCode(200).extract().response();

        Assertions.assertEquals("Hello From Sparta", response.asString());
    }

    @DisplayName("GET /api/spartans")
    @Test
    public void test1() {

        Response response= given().log().all()
                .accept(ContentType.JSON)
                .when().get("api/spartans").prettyPeek()
                .then().statusCode(200)
                .body("id",everyItem(notNullValue()))
                .contentType(ContentType.JSON).extract().response();

        log.info("GET /api/spartans");
    }

    @DisplayName("POST /api/spartans")
    @Test
    public void test2() {

       Map< String, Object> spartanPost=new HashMap<>();
        spartanPost.put("name", "Claudine");
        spartanPost.put("gender","Male");
        spartanPost.put("phone", 1672176925L);


        Response response= given().log().all()
                .accept(ContentType.JSON)
                .body(spartanPost)
                .when().post("api/spartans").prettyPeek()
                .then().statusCode(201)
                .body("data.id", notNullValue())
                .extract().response();

        log.info("GET /api/spartans");
    }
}
