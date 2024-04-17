package day04;

import org.testng.annotations.Test;
import utilities.ApiCalls;

public class C11_GetRequestDeSerializationDynamicMethod {


    ApiCalls apiCalls = new ApiCalls();
    @Test
    public void bookingTestWithDeSerialization(){

      apiCalls.derSerializationMethodForHerokuapBooking(855,200,"John","Smith", 111.0
      ,true,"2018-01-01","2019-01-01","Dinner");
    }

    @Test
    public void bookingTestWithSerialization(){
        apiCalls.serializationMethodForHerokuapBooking(855,200,"John","Smith", 111
                ,true,"2018-01-01","2019-01-01","Breakfast");
    }
}

/*
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
// POSTMAN and RestFull API Automation should be together