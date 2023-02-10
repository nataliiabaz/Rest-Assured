package com.cydeo.day07.day10;

import com.cydeo.utilities.ZipCodeTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class P03_ValueSourceTest extends ZipCodeTestBase {


    @ParameterizedTest
    @ValueSource(ints={10,20,30,40,50})
    public void test1(int number){

        System.out.println("number = " + number);
        Assertions.assertTrue(number<40);

    }

    @ParameterizedTest(name="{index} name is {0}")
    @ValueSource(strings={"Mike","John","Kimberly","King"})
    public void test2(String name) {

        System.out.println("name = " + name);
        Assertions.assertTrue(name.length()>3);
    }

    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest()
    @ValueSource(ints={22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void test3(int zipcodes) {

        given().accept(ContentType.JSON)
                .and().pathParams("zipcode", zipcodes)
                .when().get("/us/{zipcode}").prettyPeek()
                .then()
                .statusCode(200);


    }

}
