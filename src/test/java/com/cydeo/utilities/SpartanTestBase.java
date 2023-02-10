package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {


    protected Logger log = LogManager.getLogger();

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://3.80.111.193:8000";

    }
}