package com.akash.dhembare2000.tests.crud;

import com.akash.dhembare2000.base.BaseTest;
import com.akash.dhembare2000.endpoints.APIConstants;
import com.akash.dhembare2000.pojos.BookingResponse;
import groovy.beans.PropertyReader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class testCreateBookingTCPOST extends BaseTest {

    @Link(name = "Link to the TC" , url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_TBT-4")
    @TmsLink("TBT-4")
    @Owner("Akash Dhembare")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that POST request is working fine.")
    @Test
    public void testVerifyCreateBookingPOST01(){
        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response= RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));

        BookingResponse bookingResponse=payloadManager.bookingResponseJava(response.asString());

        //AssetJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("James");

        //TestNG - Assertions
        Assert.assertEquals(true,true);
        assertActions.verifyStatusCode(response,200);
    }

}
