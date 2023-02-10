package com.cydeo.day03;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework_02 extends HRTestBase {
/*
TASK 1 :
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
 */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("country_id", "US")
                .when().get("/countries/{country_id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        String countryId = response.path("country_id");

        assertEquals("US", countryId);

        String countryName = response.path("country_name");

        assertEquals("United States of America", countryName);

        int regionID = response.path("region_id");

        assertEquals(2, regionID);
    }

    /*
    TASK 2 :
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
     */

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}").when().get("/employees");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        List<String> jobId = jsonPath.getList("items.job_id");

        System.out.println("jobId = " + jobId);

        for (String eachID : jobId) {

            assertTrue(eachID.startsWith("SA"));
        }

        List<Integer> depID = jsonPath.getList("items.department_id");

        System.out.println("depID = " + depID);

        for (Integer eachDepID : depID) {

            assertEquals(80, eachDepID);
        }
    }

        /*
        TASK 3 :
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
         */

        @Test
        public void test3() {

          Response response=given().accept(ContentType.JSON)
                  .queryParam("q", "{\"region_id\":3}")
                  .when().get("/countries");

          response.prettyPrint();

          JsonPath jsonPath=response.jsonPath();

          assertEquals(200, response.statusCode());

          List<Integer> allRegionID= jsonPath.getList("items.region_id");

            System.out.println("allRegionID = " + allRegionID);

            for (Integer eachRegionID : allRegionID) {

                assertEquals(3, eachRegionID);
            }




        }

}