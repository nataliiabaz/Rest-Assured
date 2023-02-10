package com.cydeo.day03;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HRTestBase {

    @DisplayName("GET request to countries wiht using Response Path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}").
                when().get("/countries");

        List<String> allCountriesNames= response.path("items.country_name");
        System.out.println("allCountriesNames = " + allCountriesNames);

        List<Integer> allRegionID= response.path(("items.region_id"));
        System.out.println("allRegionID = " + allRegionID);
        for (Integer eachRegionId : allRegionID) {

            System.out.println("eachRegionId = " + eachRegionId);
            assertEquals(2,eachRegionId);

        }

        //stream

        assertTrue(allRegionID.stream().allMatch( each -> each==2));

        }

    }
