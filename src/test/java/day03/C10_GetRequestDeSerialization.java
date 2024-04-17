package day03;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiCalls;
import utilities.BaseUrl;
import utilities.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class C10_GetRequestDeSerialization {
    TestData testData = new TestData();
    ApiCalls apiCalls = new ApiCalls();
    BaseUrl baseUrl = new BaseUrl();

    // Convert data from json to Java ===> De-Serialization
   @Test
    public void bookingTest(){
       HashMap<String,Object>expectedData = testData.getBookingData();
       System.out.println("Expected Data");
       System.out.println(expectedData);

       // Response
       Response response = given().when().get(baseUrl.bookingUsersID(243));
       System.out.println("*******************************");
       System.out.println("JSON BODY");
       response.prettyPrint();

       // De-Serialization
       HashMap<String,Object> actualData = response.as(HashMap.class);
       System.out.println("*****************************************");
       System.out.println("After The De-Serialization");
       System.out.println(actualData);

       // Verify
       Assert.assertEquals(actualData.get("firstname"),expectedData.get("firstname"));
       Assert.assertEquals(actualData.get("lastname"),expectedData.get("lastname"));
       Assert.assertEquals(actualData.get("totalprice"),expectedData.get("totalprice"));
       Assert.assertEquals(actualData.get("depositpaid"),expectedData.get("depositpaid"));
       Assert.assertEquals(actualData.get("additionalneeds"),expectedData.get("additionalneeds"));
       Assert.assertEquals(actualData.get("checkin"),expectedData.get("checkin"));
       Assert.assertEquals(actualData.get("checkout"),expectedData.get("checkout"));
   }
}
