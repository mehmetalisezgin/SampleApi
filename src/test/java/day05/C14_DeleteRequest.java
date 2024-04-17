package day05;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.ApiCalls;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C14_DeleteRequest {
    /*
    {
    "status": "success",
    "data": "4850",
    "message": "Successfully! Record has been deleted"
}
     */

    BaseUrl baseUrl = new BaseUrl();
    ApiCalls apiCalls  = new ApiCalls();
    @Test
    public void deleteEmployee4850(){
        int id = 4850;
        Response response = given()
                .when().delete(baseUrl.deleteEmployee(id));
        response.then()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo("success"),
                        "data",equalTo(Integer.toString(id)),
                        "message",equalTo("Successfully! Record has been deleted"));
        response.prettyPrint();
    }

    @Test
    public void deleteEmployee(){
        apiCalls.deleteEmployee(4850);
    }
}
