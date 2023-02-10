package com.cydeo.day13;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookITUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P03_BookitSpecTest extends BookItTestBase {
    /**
     * Send a request /api/users/me endpoint as teacher
     * verify status code 200
     * content Type will be ContentType.JSON
     *
     */

    @Test
    public void test1() {

        given().log().all()
                .header("Authorization", BookITUtils.getTokenByRole("teacher"))
                .accept(ContentType.JSON).
                when().get("/api/users/me").prettyPeek().
                then().statusCode(200).contentType(ContentType.JSON);
    }

    @Test
    public void test2() {

        given().spec(BookITUtils.getReqSpec("teacher")).
                when().get("/api/users/me").prettyPeek().
                then().spec(BookITUtils.getResSpec(200))
                .body("firstName",is("Barbabas"));
    }
}
