package com.cydeo.day06;

import com.cydeo.utilities.CydeoTrainingTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class My_Practice02 extends CydeoTrainingTestBase {

    /**
     * Create a test called getTeachers
     * 1. Send request to GET /teacher/all
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST TEACHER INFO   ======");
     * System.out.println("====== GET FIRST TEACHER NAME  ======");
     * System.out.println("====== GET ALL TEACHER INFO  AS LIST OF MAP======");
     * System.out.println("====== FIRST TEACHER INFO======");
     * System.out.println("====== FIRST TEACHER NAME ======");
     * System.out.println("====== LAST TEACHER NAME  ======");
     */
        @DisplayName("getTeachers")
        @Test

        public void test1(){

                JsonPath jsonPath=given().log().uri()
                        .accept(ContentType.JSON)
                        .when().get("/teacher/all")
                        .then().statusCode(200).extract().jsonPath();


            List<Map<String, Object>> allTeachers = jsonPath.getList("teachers");
            for (Map<String, Object> eachTeachers : allTeachers) {
                System.out.println("eachTeachers = " + eachTeachers);
            }

            System.out.println("allTeachers.get(0) = " + allTeachers.get(0));

            System.out.println("allTeachers.get(0).get(\"firstName\") = " + allTeachers.get(0).get("firstName"));

            Map<String, Object> firstTeacher = jsonPath.getMap("teachers[0]");

            System.out.println("firstTeacher = " + firstTeacher);

            Map<String, Object> firstTeacherName = jsonPath.getMap("teachers[0]");

            System.out.println("firstTeacher.get(\"firstName\") = " + firstTeacher.get("firstName"));

            System.out.println("allTeachers.get(allTeachers.size()-1).get(\"firstName\") = " + allTeachers.get(allTeachers.size() - 1).get("firstName"));


        }

}
