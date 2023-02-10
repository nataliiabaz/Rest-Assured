package com.cydeo.day13;

import com.cydeo.utilities.NewSpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class P01_OldRestAssured extends NewSpartanTestBase {

    @Test

    public void getAllSpartan(){

        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/spartans")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(101));
    }

    @Test

    public void getAllSpartanOldWay(){

        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .expect().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(101))
                .when().get("/spartans");
    }
}
