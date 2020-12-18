import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;


public class GetRequest03 {

    /*
    Positive Scenario:
    When I send a Get request to Rest API url
    https://restful-booker.herokuapp.com/booking/1
    And Accept type is "application/json"
    Then
    HTTP Status Code should be 200
    And Response format should be "application/Json"
    And first name should be "Eric"
    And last name should be "Ericsson"
    And check in date should be "2015-09-19"
    And check out date should be "2016-12-26"

     */




    @Test
    public void test01(){
        Response response = given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking/9");
        response.prettyPrint();
    // 1.way
       response.
               then().
               assertThat().
               statusCode(200).
               contentType("application/json").
               body("firstname", Matchers.equalTo("Sally")).
               body("lastname",Matchers.equalTo("Smith")).
               body("totalprice",Matchers.equalTo(675)).
               body("depositpaid",Matchers.equalTo(true)).
               body("bookingdates.checkin",Matchers.equalTo("2016-08-23")).
               body("bookingdates.checkout",Matchers.equalTo("2018-02-22")).
               body("additionalneeds",Matchers.equalTo("Breakfast"));
     // tekrarli body kullanmadan nasil yapilir

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Sally"),
                        "lastname",Matchers.equalTo("Smith"),
                        "totalprice",Matchers.equalTo(675),
                        "depositpaid",Matchers.equalTo(true),
                        "bookingdates.checkin",Matchers.equalTo("2016-08-23"),
                        "bookingdates.checkout",Matchers.equalTo("2018-02-22"),
                        "additionalneeds",Matchers.equalTo("Breakfast"));




    //2.way
       // assertEquals(200, response.getStatusCode());



    }
}
