
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class PostRequest02 extends TestBase {
  //  2.way good

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


    @Test
    public void post01(){
        // en ic taraftaki json icin obje olusturdum
       /* bu kismi TestBase icinde kurdum  50 defa yazmak yerine bir kere yaptim
           hemde dinamik oldu degisiklik yapmak lazimsa cunku tek yerden kolaylik saglar maintanance icin
           @Before lar endpoint icin bunun icin gerek yok bana lazim olunca sadece cagiriyorum.
            note commentleri ve prettyprint kismini silerek teslim edeceksiniz.


       JSONObject jsonBookingDatesBody = new JSONObject();
        jsonBookingDatesBody.put("checkin", "2020-05-04");
        jsonBookingDatesBody.put("checkout", "2021-05-04");

        // buyuk kisim icin  JSON obje
        JSONObject jsonReqBody = new JSONObject();
        jsonReqBody.put("firstname","Alex");
        jsonReqBody.put("lastname","writer");
        jsonReqBody.put("totalprice",147);
        jsonReqBody.put("depositpaid",true);
        jsonReqBody.put("bookingdates",jsonBookingDatesBody);
        // bookingdates in value sunu yazmamiz lazim ic tarafta json oldugu icin .
        jsonReqBody.put("additionalneeds","Wifi");

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(jsonReqBody.toString()).
                when().
                post("/booking"); */


        Response response = createRequestBody(); //JSONObject class kullandik

        response.prettyPrint();

        // status code should be 200

        response.
                then().
                assertThat().
                statusCode(200);

        // assertionlari yapalim

        JsonPath json =response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("booking.firstname"),jsonReqBody.get("firstname"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.lastname"),jsonReqBody.get("lastname"),"it is not as expected");
        softAssert.assertEquals(json.getInt("booking.totalprice"),jsonReqBody.get("totalprice"),"it is not as expected");
        softAssert.assertEquals(json.getBoolean("booking.depositpaid"),jsonReqBody.get("depositpaid"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),jsonBookingDatesBody.get("checkin"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),jsonBookingDatesBody.get("checkout"),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.additionalneeds"),jsonReqBody.get("additionalneeds"),"it is not as expected");
        softAssert.assertAll();



    }

}
