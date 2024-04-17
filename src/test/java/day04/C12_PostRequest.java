package day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ApiCalls;
import utilities.BaseUrl;
import utilities.TestData;

import static io.restassured.RestAssured.given;

public class C12_PostRequest {


    TestData testData = new TestData();
    BaseUrl baseUrl = new BaseUrl();

    ApiCalls apiCalls = new ApiCalls();
    @Test
    public void createBookingTest(){
        // For the POST method (create method) we need data
        // We can create these data via JSONObject or Hashmap

        // With JSONObject
        JSONObject expectedData = testData.createBookingData();

        Response response = given()
                .contentType("application/json; Charset=utf-8")
                .auth()// Usually we need a password or token to create a data
                .basic("admin","password123")
                .body(expectedData.toString()) // if we are using JSONObject we should add .toString() method
                .when()
                .post(baseUrl.bookingCreate());
        // Now we sent to post method for creating a data but we do not know it is created or not
        // we have to verify data
        response.then()
                .assertThat()
                .statusCode(200);
        response.prettyPrint();

        // verify the created data
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(actualData.getString("booking.firstname"),expectedData.getString("firstname"));
        Assert.assertEquals(actualData.getString("booking.lastname"),expectedData.getString("lastname"));
        Assert.assertEquals(actualData.getInt("booking.totalprice"),expectedData.getInt("totalprice"));
        Assert.assertEquals(actualData.getBoolean("booking.depositpaid"),expectedData.getBoolean("depositpaid"));
        Assert.assertEquals(actualData.getString("booking.bookingdates.checkin"),expectedData.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(actualData.getString("booking.bookingdates.checkout"),expectedData.getJSONObject("bookingdates").getString("checkout"));

    }

    @Test
    public void createBookingData(){
       apiCalls.createBooking(200,"Erva Naz","Sezgin",500,false,
               "lunch","2024-04-20","2024-04-25") ;
    }

    @Test
    public void createBookingWithoutName(){
        apiCalls.createBooking(404,"","Sezgin",500,false,
                "lunch","2024-04-20","2024-04-25") ;
    }

    @Test
    public void createBookingWithoutLastName(){
        apiCalls.createBooking(404,"Erva Naz","",500,false,
                "lunch","2024-04-20","2024-04-25") ;
    }
/*
{
    "bookingid": 484,
    "booking": {
        "firstname": "Mehmet",
        "lastname": "Sezgin",
        "totalprice": 100,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2024-03-30",
            "checkout": "2024-02-28"
        },
        "additionalneeds": "Breakfast"
    }
}
 */
}
