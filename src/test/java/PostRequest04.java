import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class PostRequest04 extends TestBase {

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
    /*
    POJO : Plain Old Java Object
     bir class olusturup variable value olusturuyoruz.
    assertionlari bunun uzerinden yapacagim .
    http://www.jsonschema2pojo.org/ sayfasina giderek json i java ya cevirdik ve mavendan kutuphanleri indirdik

     */

    @Test
    public void post01() {
        Bookingdates bookingdates = new Bookingdates("2020-05-04", "2021-05-04");
        Booking booking = new Booking("Alexandre", "Sylvester", 456, true, bookingdates, "Wifi");

        Response response= given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(booking).
                when().
                post("/booking");

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath json =response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("booking.firstname"),booking.getFirstname(),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.lastname"),booking.getLastname(),"it is not as expected");
        softAssert.assertEquals(json.get("booking.totalprice"),booking.getTotalprice(),"it is not as expected"); // data uyusmazligi oldugu icin GetInt yerine sadece Get haline getirdim.
        softAssert.assertEquals(json.get("booking.totalprice"),booking.getTotalprice(),"it is not as expected"); // data uyusmazligi oldugu icin GetInt yerine sadece Get haline getirdim.
        softAssert.assertEquals(json.get("booking.depositpaid"),booking.getDepositpaid(),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),booking.getBookingdates().getCheckin(),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),booking.getBookingdates().getCheckout(),"it is not as expected");
        softAssert.assertEquals(json.getString("booking.additionalneeds"),booking.getAdditionalneeds(),"it is not as expected");
        softAssert.assertAll();

        // string istemezler icinde o yuzden ismi "Alex" i booking icindeki get methoduyla dinamic hale getirdim.
        // Booking icinde yukarda ismi deisince burada otomatik degisti .
        // yukarda ismi "Alexandre" yaptim yine gecti
        // yazdigin code sadece birsey icin calisiyorsa ona product dependent code derler bunu yapmayin
        // yukarda yazilan code ise her product icin uyumlu.
    }
}
