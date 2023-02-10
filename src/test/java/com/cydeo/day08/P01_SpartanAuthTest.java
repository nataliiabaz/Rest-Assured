package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {


    @DisplayName("GET /spartans as GUEST user")
    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(401)
                .body("error", is("Unauthorized"));


    }

    @DisplayName("GET /spartans as user")
    @Test
    public void test2() {
        //this is the way to send authorization
        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200);


    }

    @DisplayName("GET /spartans as editor")
    @Test
    public void test3() {
        //this is the way to send authorization
        given().accept(ContentType.JSON)
                .auth().basic("editor","editor")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200);

    }

    @DisplayName("Delete api/spartans/{id} as editor")
    @Test
    public void test4() {
        //this is the way to send authorization
        given().pathParam("id", 56)
                .auth().basic("editor","editor")
                .when().delete("api/spartans/{id}")
                .then().statusCode(403)
                .body("error", is("Forbidden"));

    }

    @DisplayName("Delete api/spartans/{id} as admin")
    @Test
    public void test5() {
        //this is the way to send authorization
        given().pathParam("id", 88)
                .auth().basic("admin","admin")
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);
    }

}
