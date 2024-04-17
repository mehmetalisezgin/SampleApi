package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import pojo.reqresIn.ReqresInPostDetails;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateBookingToken;

public class ApiCalls {
    // we will create dynamic methods from the Response class
    // These methods will return response and we use in the Matchers class

    BaseUrl baseUrl = new BaseUrl();

    public Response checkUserExistWithIDReqresIn(int id, int statuscode, String email, String firstname, String lastname) {
        Response response = given().when().get(baseUrl.reqresUsersID(id));
        response.then().assertThat()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK")
                .body("data.id", equalTo(id),
                        "data.email", equalTo(email),
                        "data.first_name", equalTo(firstname),
                        "data.last_name", equalTo(lastname));
        return response;

        // Variables ==>> Values changeable
    }

    public Response checkUserIDIsExist(int id, int statuscode) {
        Response response = given().when().get(baseUrl.reqresUsersID(id));
        response.then().assertThat()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK")
                .body("data.id", equalTo(id));
        return response;

    }

    // Create new method to verify data with JsonPath
    public Response checkUserExistWithIDReqresInJsonPath(int id, int statuscode, String email, String firstname, String lastname) {

        Response response = given().when().get(baseUrl.reqresUsersID(id));
        response.then()
                .assertThat()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8");

        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(actualData.getString("data.email"), email);
        Assert.assertEquals(actualData.getString("data.first_name"), firstname);
        Assert.assertEquals(actualData.getString("data.last_name"), lastname);
        return response;
    }

    public Response derSerializationMethodForHerokuapBooking(int id, int statuscode, String firstname,
                                                             String lastname, double totalprice, boolean depositpaid, String checkin,
                                                             String checkout, String additionalNeed) {
        // Let Create adynamic hasmap method
        HashMap<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("additionalneeds", additionalNeed);
        expectedData.put("bookingDates", checkin);
        expectedData.put("bookingDates", checkout);

        //Create Response class
        Response response = given().when().get(baseUrl.bookingUsersID(id));

        response.then().assertThat()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8");

        //De-Serialization convert data from java to
        HashMap<String, Object> actualData = response.as(HashMap.class);
        Assert.assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        Assert.assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        Assert.assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        Assert.assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
        Assert.assertEquals(actualData.get("checkin"), expectedData.get("checkin"));
        Assert.assertEquals(actualData.get("checkout"), expectedData.get("checkout"));
        Assert.assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));

        return response;
    }


    // SERIALIZATION converting data from Java to Json
    public Response serializationMethodForHerokuapBooking(int id, int statuscode, String firstname,
                                                          String lastname, int totalprice, boolean depositpaid, String checkin,
                                                          String checkout, String additionalNeed) {

        // We will use JSONObject class
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("additionalneeds", additionalNeed);
        expectedData.put("bookingdates", bookingDates);  // Corrected line

        Response response = given().when().get(baseUrl.bookingUsersID(id));
        response.then().assertThat()
                .statusCode(statuscode)
                .contentType("application/json; charset=utf-8");

        // Serialization
        JSONObject actualData = new JSONObject(response.getBody().asString());
        //Verify
        Assert.assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        Assert.assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        Assert.assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        Assert.assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
        Assert.assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));

        JSONObject expectedBookingDates = expectedData.getJSONObject("bookingdates");
        JSONObject actualBookingDates = actualData.getJSONObject("bookingdates");

        Assert.assertEquals(actualBookingDates.getString("checkin"), expectedBookingDates.getString("checkin"));
        Assert.assertEquals(actualBookingDates.getString("checkout"), expectedBookingDates.getString("checkout"));

        return response;
    }


    public Response createBooking(int statusCode,String firstname,String lastname,int totalPrice, boolean depositPaid,
                                  String additional,String checkin, String checkout){

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin",checkin);
        bookingDates.put("checkout",checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname",firstname );
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalPrice);
        expectedData.put("depositpaid", depositPaid);
        expectedData.put("additionalneeds", additional);
        expectedData.put("bookingdates", bookingDates);
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
                .statusCode(statusCode);
        response.prettyPrint();
        // verify the created data
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(actualData.getString("booking.firstname"),expectedData.getString("firstname"));
        Assert.assertEquals(actualData.getString("booking.lastname"),expectedData.getString("lastname"));
        Assert.assertEquals(actualData.getInt("booking.totalprice"),expectedData.getInt("totalprice"));
        Assert.assertEquals(actualData.getBoolean("booking.depositpaid"),expectedData.getBoolean("depositpaid"));
        Assert.assertEquals(actualData.getString("booking.bookingdates.checkin"),expectedData.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(actualData.getString("booking.bookingdates.checkout"),expectedData.getJSONObject("bookingdates").getString("checkout"));

        return response;
    }



    public Response createBookingWithToken(int statusCode,String firstname,String lastname,int totalPrice, boolean depositPaid,
                                  String additional,String checkin, String checkout){

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin",checkin);
        bookingDates.put("checkout",checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname",firstname );
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalPrice);
        expectedData.put("depositpaid", depositPaid);
        expectedData.put("additionalneeds", additional);
        expectedData.put("bookingdates", bookingDates);
        Response response = given()
                .contentType("application/json; Charset=utf-8")
                .header("Authorization","Bearer" + generateBookingToken())
                .body(expectedData.toString()) // if we are using JSONObject we should add .toString() method
                .when()
                .post(baseUrl.bookingCreate());
        // Now we sent to post method for creating a data but we do not know it is created or not
        // we have to verify data
        response.then()
                .assertThat()
                .statusCode(statusCode);
        response.prettyPrint();
        // verify the created data
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(actualData.getString("booking.firstname"),expectedData.getString("firstname"));
        Assert.assertEquals(actualData.getString("booking.lastname"),expectedData.getString("lastname"));
        Assert.assertEquals(actualData.getInt("booking.totalprice"),expectedData.getInt("totalprice"));
        Assert.assertEquals(actualData.getBoolean("booking.depositpaid"),expectedData.getBoolean("depositpaid"));
        Assert.assertEquals(actualData.getString("booking.bookingdates.checkin"),expectedData.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(actualData.getString("booking.bookingdates.checkout"),expectedData.getJSONObject("bookingdates").getString("checkout"));
        return response;
    }


    public Response deleteEmployee(int id){
        Response response = given()
                .when().delete(baseUrl.deleteEmployee(id));
        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo("success"),
                        "data",equalTo(Integer.toString(id)),
                        "message",equalTo("Successfully! Record has been deleted"));

        return response;
    }

    public Response createUserReqresIn(int statuscode, String name, String job){
        // we should create an object from the related pojo class
        ReqresInPostDetails expectedData = new ReqresInPostDetails();
        // we should set data to create
        expectedData.setName(name);
        expectedData.setJob(job);
        // create Response
        Response response = given().
                contentType(ContentType.JSON).
                body(expectedData).
                when().
                post(baseUrl.createReqresUser());
        response.then().assertThat().statusCode(statuscode);
        response.prettyPrint();
        // When we create a data then we should verify it
        ReqresInPostDetails actualData = response.as(ReqresInPostDetails.class);
        Assert.assertEquals(actualData.getName(),expectedData.getName());
        Assert.assertEquals(actualData.getJob(),expectedData.getJob());
        return response;
    }
}
