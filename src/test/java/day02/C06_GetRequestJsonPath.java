package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;

public class C06_GetRequestJsonPath {

    // JSONPATH
    // Json Path is also provide us testing body like Matchers Class
    // why we are using JsonPath because we can put all data in a list
    BaseUrl baseUrl = new BaseUrl();

    @Test
    public void reqresPrintalldataUser() {
        String url = "https://reqres.in/api/users";
        Response response = given().when().get(url);

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");

        // We should create an object from the Json Path and implement response in the jsonpath
        JsonPath actualData = response.jsonPath();

        // print all emails in the list
        System.out.println(actualData.getList("data.email"));
        // print all first name in the list
        System.out.println(actualData.getList("data.first_name"));

        Assert.assertTrue(actualData.getList("data.email").contains("janet.weaver@reqres.in"));
        Assert.assertTrue(actualData.getList("data.first_name").contains("Emma"));
    }

    @Test
    public void bookingUser192IsExist() {
        Response response = given().when().get(baseUrl.bookingUsersID(192));
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");

        // We should create an object from the Json Path and implement response in the jsonpath
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(actualData.getString("firstname"),"Josh");
        Assert.assertEquals(actualData.getString("lastname"),"Allen");
        Assert.assertEquals(actualData.getInt("totalprice"),111);
        Assert.assertEquals(actualData.getBoolean("depositpaid"),true);
        Assert.assertEquals(actualData.getString("bookingdates.checkin"),"2018-01-01");
        Assert.assertEquals(actualData.getString("bookingdates.checkout"),"2019-01-01");
        Assert.assertEquals(actualData.getString("additionalneeds"),"super bowls");
    }

    @Test
    public void printallReqresdataUser() {
        String url = "https://reqres.in/api/users";
        Response response = given().when().get(url);
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");

        // We should create an object from the Json Path and implement response in the jsonpath
        JsonPath actualData = response.jsonPath();

        // we can print also specific key
        System.out.println(actualData.getString("data[1].email"));
    }

}


