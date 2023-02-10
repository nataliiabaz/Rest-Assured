package com.cydeo.day_09;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){
       Response response=given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(500L)).and(lessThan(4000L)))
               .extract().response();

        System.out.println("response.getTimeIn(TimeUnit.MICROSECONDS) = " + response.getTimeIn(TimeUnit.MICROSECONDS));

        System.out.println("response.getTimeIn(TimeUnit.NANOSECONDS) = " + response.getTimeIn(TimeUnit.NANOSECONDS));


    }
}
