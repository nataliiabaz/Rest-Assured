package com.cydeo.day07.day10;

import com.cydeo.utilities.ZipCodeTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P04_CsvSource extends ZipCodeTestBase {

    @ParameterizedTest
    @CsvSource({"1,3,4",
            "2,3,5",
            "3,4,7",
            "5,6,11"})
    public void test1(int num1,int num2,int sum) {

        System.out.println(num1 + "+"+ num2+"="+sum);
        Assertions.assertEquals(sum,(num1+num2));

    }
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest()
    @CsvSource ({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})



    public void test2(String state, String city) {

        int placeNumber=given().accept(ContentType.JSON)
                .and().pathParams("state", state)
                .pathParam("city",city)
                .when().get("us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places.'place name'",everyItem(containsString(city)))
                .extract().jsonPath().getList("places").size();

        System.out.println("----------------"+city +" has "+placeNumber+ " zipcode------------");


    }
}
