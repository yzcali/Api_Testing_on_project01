import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostRequest03 extends TestBase {
  //3. way great

    /* Post Scenario
                    given accept type is Json
                    when I send a Post  request to the url
                    https://restful-booker.herokuapp.com/booking
                    with the request body
                    {
                      "firstname": "Alex",
                      "lastname" : "writer",
                      "totalprice": 147,
                      "depositpaid": true,
                      "bookingdates": {
                           "checkin": "2020-05-04",
                           "checkout": "2021-05-04"
                    },
                    "additionalneeds": "Wifi"
                    } */

    //JSONObject yerine map kullarak yapacagiz once ic taraf sonra dis taraf comple

    @Test
    public void post01(){
        /*Map<String, String> bookingDatesMap= new HashMap<>();
        bookingDatesMap.put("checkin","2020-05-04");
        bookingDatesMap.put("checkout","2021-05-04");

        Map<String,Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("firstname", "Alex");
        requestBodyMap.put("lastname", "writer");
        requestBodyMap.put("totalprice", 147);
        requestBodyMap.put("depositpaid", true);
        requestBodyMap.put("bookingdates", bookingDatesMap);
        requestBodyMap.put("additionalneeds", "Wifi");

        Response response = given().
                contentType(ContentType.JSON).// or "application/json"
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(requestBodyMap).
                when().
                post("/booking"); */
        // yukardaki kismi testbase e tasidiktan sonra o method adiyla response obje olusturdum

        
        Response response =createReqBody();

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath json =response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("booking.firstname"),requestBodyMap.get("firstname"),"it is not as expected"); // Alex ten Alexand yaptim ismi testbase den ok.
        softAssert.assertEquals(json.getString("booking.lastname"),requestBodyMap.get("lastname"),"it is not as expected");
        softAssert.assertEquals(json.getInt("booking.totalprice"),requestBodyMap.get("totalprice"),"it is not as expected");
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),requestBodyMap.get("depositpaid"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), bookingDatesMap.get("checkin"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),bookingDatesMap.get("checkout"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.additionalneeds"),requestBodyMap.get("additionalneeds"),"it is not as expected");
        softAssert.assertAll();











































    }



}
