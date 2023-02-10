package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HRTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://3.90.208.45:1000/ords/hr";

    }
}
