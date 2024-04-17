package day01;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C03_GetRequest {
    // Matchers Class
    // We are using Matchers class to verify all data within json body
    /*
    TC02: Send a GET request to String url = "https://restful-booker.herokuapp.com/booking/266";
    and verify:
    The status code is 200
    The Content type is application/json; Charset=utf-8
    The status line is HTTP/1.1 200 ok

    and verify the following values
    {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

    @Test
    public void bookingID266IsExist(){
        String url = "https://restful-booker.herokuapp.com/booking/266";
        Response response = given().when().get(url);
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .statusLine("HTTP/1.1 200 OK")
                // when we use matchers class we should implement in the body
                // we will write in the body with key value pairs
                .body("firstname", equalTo("Jane"),
                        "lastname", equalTo("Doe"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Extra pillows please"));

    }

/*
{
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
 */

    String url = "https://restful-booker.herokuapp.com/booking/";
    @Test
    public void bookingID4623IsExist(){
        int id  = 4865;
        Response response = given().when().get(url+id);
        response.prettyPrint();
        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .statusLine("HTTP/1.1 200 OK")
                // when we use matchers class we should implement in the body
                // we will write in the body with key value pairs
                .body("firstname", equalTo("Mehmet"),
                        "lastname", equalTo("Sezgin"),
                        "totalprice", equalTo(100),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2024-03-30"),
                        "bookingdates.checkout", equalTo("2024-02-28"),
                        "additionalneeds", equalTo("Breakfast"));
    }

}
