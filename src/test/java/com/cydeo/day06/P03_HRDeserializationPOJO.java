package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class P03_HRDeserializationPOJO {

    @DisplayName("GET regions to deserilization to POJO - LOMBOK - JSON PROPERTY")
    @Test
    public void test1() {

        JsonPath jsonPath = get("/regions").
                then()
                .statusCode(200)
                .extract().jsonPath();

        Region firstRegion = jsonPath.getObject("items[0]", Region.class);



    }



    @DisplayName("GET employees to deserilization to POJO with reqiured fields")
    @Test
    public void test2() {

        JsonPath jsonPath = get("/employees").
                then()
                .statusCode(200)
                .extract().jsonPath();

        Employee employee = jsonPath.getObject("items[0]", Employee.class);
        System.out.println("employee = " + employee);

    }

    /*
    TASK
    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need
     */

}
