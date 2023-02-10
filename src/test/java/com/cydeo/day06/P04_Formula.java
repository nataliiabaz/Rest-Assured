package com.cydeo.day06;

import com.cydeo.pojo.Driver;
import com.cydeo.utilities.ErgastBasePage;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_Formula extends ErgastBasePage {
        @DisplayName("Get Driver Information using JSONPATH")
        @Test
        public void test1() {

            /*
            - Given accept type is json
- And path param driverId is alonso
- When user send request /drivers/{driverId}.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8 - And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish
             */

            JsonPath jsonPath = given().log().all().accept(ContentType.JSON)
                    .pathParam("driverId", "alonso")
                    .when().get("/drivers/{driverId}.json")
                    .then().log().all()
                    .statusCode(200)
                    .contentType("application/json; charset=utf-8")
                    .extract().jsonPath();


           assertEquals("1", jsonPath.getString("MRData.total") );

           assertEquals("Fernando", jsonPath.getString("MRData.DriverTable.Drivers[0].givenName"));

            assertEquals("Alonso", jsonPath.getString("MRData.DriverTable.Drivers[0].familyName"));

            assertEquals("Spanish", jsonPath.getString("MRData.DriverTable.Drivers[0].nationality"));
        }


     /*
            - Given accept type is json
- And path param driverId is alonso
- When user send request /drivers/{driverId}.json
- Then verify status code is 200
- And content type is application/json; charset=utf-8 - And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish
             */

    @DisplayName("Get Driver Information using HAMCREST MATCHERS ")
    @Test
    public void task2() {

        Response response=given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .body("MRData.total",is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", is("Fernando"))
                .body("MRData.DriverTable.Drivers[0].familyName", is("Alonso"))
                .body("MRData.DriverTable.Drivers[0].nationality", is("Spanish"))
                .extract().response();

        response.prettyPeek();
        //Print givenName of Driver by using extract method after HamCrest
        String givenName=response.path("MRData.DriverTable.Drivers[0].givenName");
        System.out.println("givenName = " + givenName);

    }

    @DisplayName("Convert Driver information to Java Structure")
    @Test

    public void test3(){

        JsonPath jsonPath = given().log().all().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then().log().all()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();



        Map<String,Object> driverMap=jsonPath.getMap("MRData");

        System.out.println("driverMap = " + driverMap);

    }

    @DisplayName("Convert Driver information POJO Class")
    @Test
    public void test4() {

        /*
         And total is 1
- And givenName is Fernando
- And familyName is Alonso
- And nationality is Spanish
             */

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}.json")
                .then().log().ifValidationFails()
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .extract().response().jsonPath();


        Driver total = jsonPath.getObject("MRData", Driver.class);
        assertEquals("1", total.getTotal());

        Driver driver = jsonPath.getObject("MRData.DriverTable.Drivers[0]", Driver.class);
        assertEquals("Fernando", driver.getGivenName());
        assertEquals("Alonso", driver.getFamilyName());
        assertEquals("Spanish", driver.getNationality());


    }


    }
