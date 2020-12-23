import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class PostRequest01 extends TestBase{
    /*
    create post request
    1.Endpoint
    2.Request body
    3.Authorization
    4.accept type sometimes is used or not
    5.content type smt is used or not

    note : get ile post request lerin farklari nedir ?
          
     */

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
    }
    "additionalneeds": "Wifi"
    }

    then
    Statuscode should be 200
    and response body should match with the request body.
     */

   //1. way not good
    @Test
    public void post01(){

    String jsonReqBody= "{\n" +
                   "      \"firstname\": \"Alex\",\n" +
                   "      \"lastname\" : \"writer\",\n" +
                   "      \"totalprice\": 147,\n" +
                   "      \"depositpaid\": true,\n" +
                   "      \"bookingdates\": {\n" +
                   "           \"checkin\": \"2020-05-04\",\n" +
                   "           \"checkout\": \"2021-05-04\"\n" +
                   "    },\n" +
                   "    \"additionalneeds\": \"Wifi\"\n" +
                   "    }";

        Response response= given().
                            contentType(ContentType.JSON). // contentType icerik tipi database e yolladigin datanin icerigi Json
                            spec(spec01).
                            auth().
                            basic("admin","password123").
                            body(jsonReqBody).
                            when().
                            post("/booking");

      // testcase de endpoint nerde bitiyor onu unutmayin  booking i ekledik.

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath json =response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("booking.firstname"),"Alex","it is not as expected");
        softAssert.assertEquals(json.getString("booking.lastname"),"writer","it is not as expected");
        softAssert.assertEquals(json.getInt("booking.totalprice"),147,"it is not as expected");
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),true,"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2020-05-04","it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),"2021-05-04","it is not as expected");
        softAssert.assertEquals(json.getString("booking.additionalneeds"),"Wifi","it is not as expected");
        softAssert.assertAll();




    }






















}
