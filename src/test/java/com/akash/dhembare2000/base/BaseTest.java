package com.akash.dhembare2000.base;


import com.akash.dhembare2000.asserts.AssertActions;

import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.*;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// Common to all TestCases
//Base Test father -> Testcase - Son - Single Inheritance
public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;



    @BeforeTest
    public void setUP() {

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

        //OR

//        requestSpecification = RestAssured.given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();




    }

    public String getToken(){
        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        //Setting the payload
        String payload= payloadManager.setAuthPayload();

        // Get the Token
        response=requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // Token Extraction
        String token=payloadManager.getTokenFromJSON(response.asString());

        return token;
    }
}
