package com.cydeo.day07.day04;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.jar.JarEntry;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_HRWithJsonPath extends HRTestBase {

    @DisplayName("Get All employees with Query Param limit = 200 --> JSONPATH ")
    @Test
    public void test1() {

        /*
         //Status code 200

        // get all emails from response

        //get all emails who is working as IT_PROG

        // get me all employees first names whose salary is more than 10000

        // get me all information from response  who has max salary

        // get me firstname from response  who has max salary
         */

        Response response=given().accept(ContentType.JSON)
                .and()
                .queryParam("limit",200).
                when().get("/employees") ;

        response.prettyPrint();

        assertEquals(200, response.statusCode());

        JsonPath jsonPath=response.jsonPath();

        List<String> allEmails= jsonPath.getList("items.email");

        System.out.println("allEmails = " + allEmails);

        System.out.println("allEmails.size() = " + allEmails.size());


        //List<String> EmailIt=jsonPath.getList("items.findAll {job_id==IT_PROG}.email");


        List<String> allITEmails = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");

        System.out.println("allITEmails.size() = " + allITEmails.size());

        //get me all employees first names whose salary is more than 10000

        List<String> allEmployeesSalaryMoreThan10000=jsonPath.getList("items.findAll {it.salary>=10000}.first_name");

        System.out.println("allEmployeesSalaryMoreThan10000 = " + allEmployeesSalaryMoreThan10000);

        System.out.println("jsonPath.getString(\"items.max{it.salary}\") = " + jsonPath.getString("items.max{it.salary}"));

        System.out.println("jsonPath.getString(\"items.max{it.salary}.first_name\") = " + jsonPath.getString("items.max{it.salary}.first_name"));


        System.out.println("jsonPath.getString(\"items.min{it.salary}\") = " + jsonPath.getString("items.min{it.salary}"));

        System.out.println("jsonPath.getString(\"items.min{it.salary}.first_name\") = " + jsonPath.getString("items.min{it.salary}.first_name"));

    }

}
