package day05;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.booking.BookingDates;
import pojo.booking.BookingDetails;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;

public class C15_PojoGetRequest {
    /*
    POJO : Stands for Plain Old Java Object, In the context of using a REST API in Java.
    a POJO refers to a simple Java Class that encapsulates data and provides getter, setter, toString()
    methods for accessing and modifying data.
    Why we are using POJO?
    - Data Representation
    - Serialization
    - Encapsulation
    - Compatibility
     */

   BaseUrl baseUrl = new BaseUrl();
    @Test
    public void getBookingTest() {
        // We created pojo class with name BookingDetails
        // Create an object from that class
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");

        BookingDetails expectedData = new BookingDetails();
        // We should set data to verify
        expectedData.setId(427);
        expectedData.setFirstname("Josh");
        expectedData.setLastname("Allen");
        expectedData.setTotalprice(111);
        expectedData.setAdditionalneeds("super bowls");
        expectedData.setBookingDates(dates);

        Response response = given().when().get(baseUrl.bookingUsersID(427));
        response.prettyPrint();
        // verify with jsonpath
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(actualData.getString("firstname"),expectedData.getFirstname());
        Assert.assertEquals(actualData.getString("lastname"),expectedData.getLastname());
        Assert.assertEquals(actualData.getInt("totalprice"),expectedData.getTotalprice());
        Assert.assertEquals(actualData.getString("additionalneeds"),expectedData.getAdditionalneeds());
    }


    @Test
    public void getBookingTest02() {
        // We created pojo class with name BookingDetails
        // Create an object from that class
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");

        BookingDetails expectedData = new BookingDetails();
        // We should set data to verify
        expectedData.setId(427);
        expectedData.setFirstname("Bob");
        expectedData.setLastname("Smith");
        expectedData.setTotalprice(111);
        expectedData.setAdditionalneeds("Breakfast");
        expectedData.setBookingDates(dates);

        Response response = given().when().get(baseUrl.bookingUsersID(427));
        response.prettyPrint();
        // verify with jsonpath
        BookingDetails actualData = response.as(BookingDetails.class);
        Assert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        Assert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        Assert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        Assert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());
    }

//NOTE: No need to use JSONObject, JsonPath, Hashmap for GET,POST,DELETE etc. When we create POJO Classes
}
