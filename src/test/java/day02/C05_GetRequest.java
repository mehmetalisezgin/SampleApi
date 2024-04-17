package day02;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C05_GetRequest {

   // Header Test ==> status code, Contenttype, Status line, gettime
   BaseUrl baseUrl = new BaseUrl();
   @Test
   public void headerTest(){
    Response response = given().when().get(baseUrl.reqresUsersID(3));
    response.prettyPrint();
    // first way to test header
       Assert.assertEquals(response.statusCode(),200);
       Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
       Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");

      // second way to test header
      response.then()
              .statusCode(200)
              .statusLine("HTTP/1.1 200 OK")
              .contentType("application/json; charset=utf-8");


   }

   @Test
    public void reqresUser3dataTest(){
       Response response = given().when().get(baseUrl.reqresUsersID(3));
       response.then()
               .statusCode(200)
               .statusLine("HTTP/1.1 200 OK")
               .contentType("application/json; charset=utf-8")
               .body("data.email", equalTo("emma.wong@reqres.in"),
                       "data.first_name",equalTo("Emma"));
   }
}
