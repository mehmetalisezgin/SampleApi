package day06;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.reqresIn.ReqresInPostDetails;
import utilities.ApiCalls;
import utilities.BaseUrl;
import utilities.TestData;

import static io.restassured.RestAssured.given;

public class C16_PostRequestWithPojo {


    BaseUrl baseUrl = new BaseUrl();
    ApiCalls apiCalls = new ApiCalls();
    @Test
    public void createuserReqresIn(){
        // we should create an object from the related pojo class
        ReqresInPostDetails expectedData = new ReqresInPostDetails();
        // we should set data to create
        expectedData.setName("Hamza");
        expectedData.setJob("Tester");
        // create Response
        Response response = given().
                contentType(ContentType.JSON).
                body(expectedData).
                when().
                post(baseUrl.createReqresUser());
        response.prettyPrint();
        // When we create a data then we should verify it
        ReqresInPostDetails actualData = response.as(ReqresInPostDetails.class);
        Assert.assertEquals(actualData.getName(),expectedData.getName());
        Assert.assertEquals(actualData.getJob(),expectedData.getJob());
    }


    @Test
    public void createUserReqresIn(){
        apiCalls.createUserReqresIn(201,"Mehmet","Mathematics");
    }
   /*
   The name should be more than 4 char, lower than 12 char
   The name can not be empty
   The job should be more than 5 char, lower than 12 char
   The job con not be empty
   When we create this scenario the response should return 404
    */

    // LONG WAY one by one we can create test methods

    @Test
    public void nameLowerThan4Char(){
        apiCalls.createUserReqresIn(404,"Meh","Mathematics");
    }
    @Test
    public void nameMoreThan12Char(){
        apiCalls.createUserReqresIn(404,"MehmetAliSezgin","Mathematics");
    }

    @Test
    public void nameIsMissing(){
        apiCalls.createUserReqresIn(404,"","Mathematics");
    }

    @Test
    public void jobLowerThan4Char(){
        apiCalls.createUserReqresIn(404,"Meh","Mat");
    }
    @Test
    public void jobnameMoreThan12Char(){
        apiCalls.createUserReqresIn(404,"MehmetAliSezgin","MathematicsTester");
    }

    @Test
    public void jobIsMissing(){
        apiCalls.createUserReqresIn(404,"Mehmet","");
    }
    @Test
    public void jobAndNameMissing(){
        apiCalls.createUserReqresIn(404,"","");
    }


    @Test(dataProvider = "createUserReqres",dataProviderClass = TestData.class)
    public void negativeUserTestReqresIn(int statuscode,String name,String job){
        apiCalls.createUserReqresIn(statuscode,name,job);
    }






}
