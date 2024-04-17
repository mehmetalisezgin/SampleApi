package day01;


import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest {
   /*
   TC01: Send a Get request to https://restful-booker.herokuapp.com/booking/125
   and verify
   The status code is 200
   The Content type is application/json; Charset=utf-8
   The Value of Header named 'Server is Cowboy'
   The status line is HTTP/1.1 200 ok
   The Time of the response
    */

    String url = "https://restful-booker.herokuapp.com/booking/266";
    Response response = given().when().get(url);


    // how to get information of the header in an API query
    @Test
    public void printInfoRestfulBooking(){
        System.out.println("The Status Code is :"+response.statusCode());
        System.out.println("The Content type is :"+ response.contentType());
        System.out.println("The Value of Header is :"+response.header("Server"));
        System.out.println("The status line is :"+response.statusLine());
        System.out.println("The Time of The Response is :"+response.getTime());
    }

    // The information of the header tested one by one with Assertion
    @Test
    public void headerInfoTestRestfulBooking(){
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"Cowboy");
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");
    }

    // we can test in the response class
    @Test
    public void headerInfoTest(){
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");
    }



}
