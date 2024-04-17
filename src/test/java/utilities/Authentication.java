package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateBookingToken() {
        String url = "https://restful-booker.herokuapp.com/auth";
        String username = "admin";
        String password = "password123";
        // we need to create hashmap to implement username and password in the response
        Map<String, Object> tokenBody = new HashMap<>();
        tokenBody.put("username", username);
        tokenBody.put("password", password);

        Response response = given().contentType(ContentType.JSON)
                .body(tokenBody)// no need to add .toString method because it is not JSONObject
                .when().post(url);
        // Create JsonPath to get token as a string
        JsonPath responseToken = response.jsonPath();
        String token = responseToken.getString("token");
        return token;
    }

    @Test
    public void tokenTest(){
        System.out.println(generateBookingToken());

    }
}
