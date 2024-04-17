package day03;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiCalls;
import utilities.BaseUrl;
import utilities.TestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C09_GetRequestDeSerialization {

    // important interview questions
    // what is de-serialization and serialization
    // Convert data from json to Java ===> De-Serialization
    // Convert data from Java to json ===> Serialization

    // We will create hashmap method for verify

    TestData testData = new TestData();
    ApiCalls apiCalls = new ApiCalls();
    BaseUrl baseUrl = new BaseUrl();
    @Test
    public void testWithHashMapDeserialzation(){

        // This is expected data using Hashmap from TestData class
        HashMap<String,Object> expectedData = testData.verifyUserReqresIn();

        // Request and Response
        Response response = given().when().get(baseUrl.reqresUsersID(2));
        System.out.println("This Body is Json Body Coming From Response");
        response.prettyPrint();
        System.out.println("************************************");
        System.out.println("This Body is Hash Map Body Created in the TestData Class");
        System.out.println(expectedData);

        // Convert data from json to Java ===> De-Serialization
        // Create a Hash map
        HashMap<String, Object> responseData = response.as(HashMap.class);// De-Serialization
        System.out.println("****************************************");
        System.out.println(responseData);
        HashMap<String, Object> actualData = (HashMap<String, Object>) responseData.get("data");
        System.out.println(actualData);
        //Verify
        Assert.assertEquals(actualData.get("data.email"),expectedData.get("email"));
        Assert.assertEquals(actualData.get("data.first_name"),expectedData.get("first_name"));
        Assert.assertEquals(actualData.get("data.last_name"),expectedData.get("last_name"));

    }

}
