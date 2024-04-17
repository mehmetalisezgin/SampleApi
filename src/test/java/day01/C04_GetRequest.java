package day01;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C04_GetRequest {

    // we created url methods in the BaseUrl class then we need to create object
    BaseUrl baseUrl = new BaseUrl();

    /*
    {
    "data": {
        "id": 1,
        "email": "george.bluth@reqres.in",
        "first_name": "George",
        "last_name": "Bluth",
        "avatar": "https://reqres.in/img/faces/1-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
     */

    @Test
    public void reqresUserTest(){
        Response response = given().when().get(baseUrl.reqresUsersID(1));
        response.prettyPrint();
        response.then().assertThat().statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .body("data.email", equalTo("george.bluth@reqres.in"),
                        "data.first_name",equalTo("George"),
                        "data.last_name",equalTo("Bluth"));
    }


}
