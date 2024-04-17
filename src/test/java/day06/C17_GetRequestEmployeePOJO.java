package day06;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.employee.EmployeeData;
import pojo.employee.EmployeeDetail;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;

public class C17_GetRequestEmployeePOJO {

    BaseUrl baseUrl = new BaseUrl();

    @Test
    public void employeeIsExist(){
        // Create Object from the Pojo classes
        // If we have inner json body we should separate their pojo classes
        // we should implement inner pojo to large pojo

        // this is inner json body (inner pojo)
        EmployeeData data = new EmployeeData();
        data.setId(1);
        data.setEmployee_name("Tiger Nixon");
        data.setEmployee_salary(320800);
        data.setEmployee_age(61);

        // large json body
        EmployeeDetail expectedData = new EmployeeDetail();
        expectedData.setStatus("success");
        expectedData.setMessage("Successfully! Record has been fetched.");
        expectedData.setData(data);

        // Response Class
        Response response = given().when().get(baseUrl.getEmployee(1));
        response.then().assertThat().statusCode(200);

        EmployeeDetail actualData = response.as(EmployeeDetail.class);
        Assert.assertEquals(actualData.getStatus(),expectedData.getStatus());
        Assert.assertEquals(actualData.getMessage(),expectedData.getMessage());
        Assert.assertEquals(actualData.getData().getId(),expectedData.getData().getId());
        Assert.assertEquals(actualData.getData().getEmployee_name(),expectedData.getData().getEmployee_name());
        Assert.assertEquals(actualData.getData().getEmployee_salary(),expectedData.getData().getEmployee_salary());
        Assert.assertEquals(actualData.getData().getEmployee_age(),expectedData.getData().getEmployee_age());
    }


    /*
    {
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}
     */

}
