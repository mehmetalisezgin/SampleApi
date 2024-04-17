package day01;


import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class C01_GetRequest {

   /*
   NOTE: To create API queries in the Intellij the io.RestAssured library
   is used and we need to create object from the Response class
   Response response = given().when().get(url);
   given() : It refers to initial values given to us when starting test
   when()  : It refers to the operations when we performed in our test
   then()  : It refers to the actions taken to evaluate the response values
   and()   : represents interconnected operations
    */

    @Test
    public void getAllUsersReqresIn(){
        // https://reqres.in/api/users
        String url = "https://reqres.in/api/users";
        Response response = given().when().get(url);
        response.prettyPrint(); // It brings us only data with json body
        //response.prettyPeek();  // It brings us all data and information of header
       // response.print(); // not useful
    }


}
