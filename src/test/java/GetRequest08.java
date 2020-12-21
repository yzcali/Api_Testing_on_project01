import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;


public class GetRequest08 extends TestBase{

    /*
      When I send a GET request to REST API URL
    https://restful-booker.herokuapp.com/booking/5
    Then
    HTTP Status Code should be 200
    And Response format should be "application/Json"
     {
    "firstname": "Mary",
    "lastname": "Jackson",
    "totalprice": 275,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2015-04-18",
        "checkout": "2017-07-28"
    }
     */


    @Test
    public void get01(){
        // method icierisinde string yazilmasini istemezler get("/booking/5"); gibi
        spec01.pathParam("bookingid", 5);

        Response response =given().
                spec(spec01).
                when().
                get("/booking/{bookingid}");



        response.prettyPrint();

        JsonPath json= response.jsonPath();
        System.out.println(json.getString("firstname"));
        assertEquals("firstname is not as expected","Jim",json.getString("firstname"));

        System.out.println(json.getString("lastname"));
        assertEquals("lastname is not as expected","Wilson",json.getString("lastname"));

        System.out.println(json.getInt("totalprice"));
        assertEquals("totalprice is not as expected", 242,json.getInt("totalprice"));


        System.out.println(json.getBoolean("depositpaid"));
        assertEquals("depositpaid is not as expected",false,json.getBoolean("depositpaid"));


        System.out.println(json.getString("bookingdates.checkin"));
        assertEquals("checkin is not expected","2018-05-23",json.getString("bookingdates.checkin"));

        System.out.println(json.getString("bookingdates.checkout"));
        assertEquals("checkout is not as expected","2019-09-01",json.getString("bookingdates.checkout"));











    }

}
