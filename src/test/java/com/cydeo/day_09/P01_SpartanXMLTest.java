package com.cydeo.day_09;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_SpartanXMLTest extends SpartanTestBase {

    /**
     * Given accept type is application/xml
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     *   print firstname
     *   .....
     *   ...
     */


    @Test
    public void test1() {

        given().accept(ContentType.XML)
                .auth().basic("admin","admin").
                when().get("/api/spartans").prettyPeek().
                then().statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name",is("John Dan"))
                .body("List.item[0].gender",is("Male"));


    }

    @DisplayName("GET /api/spartans with using XMLPath")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin").
                when().get("/api/spartans");


        XmlPath xmlPath=response.xmlPath();

        System.out.println("xmlPath.getString(\"List.item[0].name\") = " + xmlPath.getString("List.item[0].name"));

        System.out.println("xmlPath.getString(\"List.item[2].name\") = " + xmlPath.getString("List.item[2].name"));

        System.out.println("xmlPath.getString(\"List.item[-1].name\") = " + xmlPath.getString("List.item[-1].name"));

        List<String> nameList= xmlPath.getList("List.item.name");

        System.out.println("nameList = " + nameList);

        List<Spartan> allSpartans=xmlPath.getList("List.item");

        System.out.println("allSpartans.size() = " + allSpartans.size());
    }
}
