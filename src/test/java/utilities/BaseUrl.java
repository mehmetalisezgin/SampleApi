package utilities;

public class BaseUrl {

    // we will keep all url in this class within a string method

    public String reqresUsersID(int id){
        String url = "https://reqres.in/api/users/"+id;
        return url;
    }
    public String bookingUsersID(int id){
        String url = "https://restful-booker.herokuapp.com/booking/"+id;
        return url ;
    }


    public String bookingCreate(){
        String url = "https://restful-booker.herokuapp.com/booking";
        return url ;
    }


    public String deleteEmployee(int id){
        String url = "https://dummy.restapiexample.com/api/v1/delete/"+id;
        return url;
    }

    public String createReqresUser(){
        String url = "https://reqres.in/api/users";
        return url;
    }


    public String getEmployee(int id){
        String url = "https://dummy.restapiexample.com/api/v1/employee/"+id;
        return url;
    }
}
