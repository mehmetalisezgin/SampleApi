package utilities;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class TestData {

    @DataProvider(name = "userDataProvider")
    public Object[][] createUserData() {
        return new Object[][]{
                {1, 200, "george.bluth@reqres.in", "George", "Bluth"},
                {2, 200, "janet.weaver@reqres.in", "Janet", "Weaver"},
                {3, 200, "emma.wong@reqres.in", "Emma", "Wong"},
                {4, 200, "eve.holt@reqres.in", "Eve", "Holt"},
                {5, 200, "charles.morris@reqres.in", "Charles", "Morris"},
                {6, 200, "tracey.ramos@reqres.in", "Tracey", "Ramos"}
        };
    }


    // Hash Map method for the test data
    public HashMap<String, Object> verifyUserReqresIn() {
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("id", 2);
        expectedData.put("email", "janet.weaver@reqres.in");
        expectedData.put("first_name", "Janet");
        expectedData.put("last_name", "Weaver");
        return expectedData;
    }


    public HashMap<String,Object> getBookingData(){
        HashMap<String,Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");

        HashMap<String, Object>expectedData = new HashMap<>();
        expectedData.put("firstname", "Josh");
        expectedData.put("lastname", "Allen");
        expectedData.put("totalprice", 111.0);
        expectedData.put("depositpaid", true);
        expectedData.put("additionalneeds", "super bowls");
        expectedData.put("bookingdates", bookingDates);

        return expectedData;
    }


    public JSONObject createBookingData(){
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2024-04-12");
        bookingDates.put("checkout","2024-04-15");

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", "Asli");
        expectedData.put("lastname", "Sezgin");
        expectedData.put("totalprice", 540);
        expectedData.put("depositpaid", true);
        expectedData.put("additionalneeds", "breakfast");
        expectedData.put("bookingdates", bookingDates);

        return expectedData;
    }

    @DataProvider(name = "createUserReqres")
    public Object[][] createUserReqres() {
        return new Object[][]{
                { 404, "Meh","Mathematics"},
                { 404, "MehmetAliSezgin","Mathematics"},
                { 404, "","Mathematics"},
                { 404, "Meh","Mat"},
                { 404, "MehmetAliSezgin","MathematicsTester"},
                { 404, "Mehmet",""},
                { 404, "",""}
        };
    }



}
