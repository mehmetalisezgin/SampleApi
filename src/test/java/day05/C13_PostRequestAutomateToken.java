package day05;

import org.testng.annotations.Test;
import utilities.ApiCalls;

public class C13_PostRequestAutomateToken {
    // Create a class with name Authentication in the utilities package
    // When we use post method usually the url is asking username and password or token
    // Token usually changes in 6-12-24 hours automatically
    // thats why we need to automate it

    ApiCalls apiCalls = new ApiCalls();
    @Test
    public void createBookingData(){
     apiCalls.createBookingWithToken(200,"Polina","Candir",750,true,
             "Lunch","2024-05-01","2024-05-06");
    }

}
