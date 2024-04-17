package day02;

import org.testng.annotations.Test;
import utilities.ApiCalls;

public class C07_GetDynamicRequest {


    ApiCalls apiCalls = new ApiCalls();

    // Verify the data of the user id 1 in the reqres users
    @Test
    public void userID1IsExist(){
        apiCalls.checkUserExistWithIDReqresIn(1,200,"george.bluth@reqres.in",
                "George","Bluth");
    }

    // Verify the data of the user id 2 in the reqres users

    @Test
    public void userID2IsExist(){
        apiCalls.checkUserExistWithIDReqresIn(2,200,"janet.weaver@reqres.in",
                "Janet","Weaver");
    }

    @Test
    public void userID3IsExist(){
        apiCalls.checkUserIDIsExist(3,200);
    }

    // Verify the data of the user id 4 in the reqres users
    @Test
    public void userID4IsExist(){
        apiCalls.checkUserExistWithIDReqresIn(4,
                200, "eve.holt@reqres.in","Eve","Holt");
    }


}
